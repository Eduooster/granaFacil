package org.example.granafacil.core.application.usecases.openFinance;

import org.example.granafacil.core.application.gateways.ConexaoOpenFinanceRepository;
import org.example.granafacil.core.application.gateways.ContaFinanceiraRepository;
import org.example.granafacil.core.application.gateways.SincronizarContaRepository;
import org.example.granafacil.core.domain.entities.ConexaoOpenFinance;
import org.example.granafacil.core.domain.entities.ContaFinanceira;
import org.example.granafacil.core.domain.entities.SincronizacaoConta;
import org.example.granafacil.core.domain.enums.StatusSincronizacao;
import org.example.granafacil.core.domain.enums.TipoSincronizacao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.List;
import java.util.Optional;


public class SincronizarTransacoesConexaoUseCase {

    private static final Logger log = LoggerFactory.getLogger(SincronizarTransacoesConexaoUseCase.class);
    private SincronizarContaRepository syncgateway;
    private final ConexaoOpenFinanceRepository conexaoOpenFinanceRepository;
    private final ExecutarSincronizacaoTransacaoUseCase executarSincronizacaoTransacaoUseCase;
    private final ContaFinanceiraRepository contaFinanceiraRepository;


    public SincronizarTransacoesConexaoUseCase(SincronizarContaRepository gateway, ConexaoOpenFinanceRepository conexaoOpenFinanceRepository, ExecutarSincronizacaoTransacaoUseCase executarSincronizacaoTransacaoUseCase, ContaFinanceiraRepository contaFinanceiraRepository) {
        this.syncgateway = gateway;

        this.conexaoOpenFinanceRepository = conexaoOpenFinanceRepository;
        this.executarSincronizacaoTransacaoUseCase = executarSincronizacaoTransacaoUseCase;

        this.contaFinanceiraRepository = contaFinanceiraRepository;
    }

    public void execute (String conexaoOpenFinanceId) {

        log.info("Anlisando conexao com id {}", conexaoOpenFinanceId);


        Optional<ConexaoOpenFinance> teste = conexaoOpenFinanceRepository.findByPluggyItemIdAndAtivoTrue(conexaoOpenFinanceId);

        log.info("Resultado da busca: {}", teste);

        List<ContaFinanceira> contasDaConexao =
                contaFinanceiraRepository.findByConexaoOpenFinanceId(teste.get().getId());



        for (ContaFinanceira conta : contasDaConexao) {

                SincronizacaoConta syncTransacao = SincronizacaoConta.iniciar(
                        teste.get(),
                        conta,
                        TipoSincronizacao.TRANSACOES
                );

                syncTransacao.setStatus(StatusSincronizacao.EM_ANDAMENTO);
                syncTransacao.setStartedAt(Instant.now());

                boolean sucesso = true;

                log.info("sync transacao insntacniada : {}", syncTransacao);

                try {
                    log.info("Chamando executar transacao");
                    executarSincronizacaoTransacaoUseCase.execute(syncTransacao);

                } catch (Exception e) {
                    sucesso = false;
                    syncTransacao.setStatus(StatusSincronizacao.ERRO);
                    syncTransacao.setErro(
                            e.getMessage() != null ? e.getMessage() : "Erro inesperado na sincronização"
                    );
                } finally {
                    syncTransacao.setFinishedAt(Instant.now());
                    syncTransacao.setUpdatedAt(Instant.now());

                    if (sucesso){
                        syncTransacao.setStatus(StatusSincronizacao.CONCLUIDO);
                        syncTransacao.setUltimaDataSync(Instant.now());
                    }

                    syncgateway.save(syncTransacao);
                }
            }


        }
}
