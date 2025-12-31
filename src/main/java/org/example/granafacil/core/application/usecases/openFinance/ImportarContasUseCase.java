package org.example.granafacil.core.application.usecases.openFinance;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.granafacil.core.application.dtos.PluggyDtos.PluggyItemAccounts;
import org.example.granafacil.core.application.gateways.*;
import org.example.granafacil.core.domain.entities.*;
import org.example.granafacil.core.domain.enums.ProviderAccount;
import org.example.granafacil.core.domain.enums.StatusSincronizacao;
import org.example.granafacil.core.domain.enums.TipoSincronizacao;

import java.time.Instant;
import java.util.List;


@Slf4j
public class ImportarContasUseCase {

    private final ContaFinanceiraRepository contaFinanceiraRepository;
    private final SincronizarContaRepository sincronizarContaRepository;
    private final PluggyGateway pluggyGateway;
    private final ConexaoOpenFinanceRepository conexaoOpenFinanceRepository;

    public ImportarContasUseCase(
            ContaFinanceiraRepository contaFinanceiraRepository,
            SincronizarContaRepository sincronizarContaRepository,
            PluggyGateway pluggyGateway,
            ConexaoOpenFinanceRepository conexaoOpenFinanceRepository
    ) {
        this.contaFinanceiraRepository = contaFinanceiraRepository;
        this.sincronizarContaRepository = sincronizarContaRepository;
        this.pluggyGateway = pluggyGateway;
        this.conexaoOpenFinanceRepository = conexaoOpenFinanceRepository;
    }

    public void execute(ConexaoOpenFinance conexaoOpenFinance) {

        log.info("Conexao Open Finance: {}", conexaoOpenFinance.getId());

        ConexaoOpenFinance conexaoGerenciada = conexaoOpenFinanceRepository.findById(conexaoOpenFinance.getId())
                .orElseThrow(() -> new IllegalStateException("Conexão não encontrada"));

        SincronizacaoConta sync = iniciarSincronizacao(conexaoGerenciada);

        List<PluggyItemAccounts> contas;

        try {
            contas = pluggyGateway.getItemsAccount(conexaoGerenciada.getPluggyItemId());
        } catch (Exception e) {
            finalizarComErro(sync, e.getMessage());
            throw e;
        }

        boolean houveErro = false;

        for (PluggyItemAccounts c : contas) {
            try {
                processarConta(conexaoGerenciada, c);
            } catch (Exception e) {
                houveErro = true;
                log.error("Erro ao importar conta {}", c.getNumber(), e);
            }
        }

        if (houveErro) {
            finalizarComErro(sync, "Erro ao sincronizar uma ou mais contas");
        } else {
            finalizarComSucesso(sync);
        }
    }


    @Transactional
    protected SincronizacaoConta iniciarSincronizacao(ConexaoOpenFinance conexao) {
        SincronizacaoConta sync = SincronizacaoConta.iniciar(
                conexao,
                null,
                TipoSincronizacao.CONTAS
        );
        sync.setStatus(StatusSincronizacao.EM_ANDAMENTO);
        sync.setStartedAt(Instant.now());
        return sincronizarContaRepository.save(sync);
    }

    @Transactional
    protected void processarConta(ConexaoOpenFinance conexao, PluggyItemAccounts c) {

        boolean jaExiste = contaFinanceiraRepository.existsByUsuarioIdAndIdConnectorAndAccountNumber(
                conexao.getUsuario().getId(),
                conexao.getInstituicaoFinanceira().getIdConnector(),
                c.getNumber()
        );

        if (jaExiste) {
            return;
        }

        ContaFinanceira conta = ContaFinanceira.novo(
                conexao.getUsuario(),
                conexao.getInstituicaoFinanceira(),
                c.getType(),
                c.getSubtype(),
                c.getName(),
                c.getBalance(),
                c.getId(),
                ProviderAccount.EXTERNAL,
                c.getNumber(),
                conexao
        );

        contaFinanceiraRepository.save(conta);
    }

    @Transactional
    protected void finalizarComSucesso(SincronizacaoConta sync) {
        sync.finalizarComSucesso();
        sync.setUpdatedAt(Instant.now());
        sync.setUltimaDataSync(Instant.now());
        sincronizarContaRepository.save(sync);
    }

    @Transactional
    protected void finalizarComErro(SincronizacaoConta sync, String mensagemErro) {
        sync.finalizarComErro(mensagemErro);
        sync.setUpdatedAt(Instant.now());
        sincronizarContaRepository.save(sync);
    }
}
