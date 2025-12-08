package org.example.granafacil.core.application.usecases.OpenFinanceUseCases;

import lombok.extern.slf4j.Slf4j;
import org.example.granafacil.core.application.dtos.TransactionDTO;
import org.example.granafacil.core.application.dtos.TransactionsResponse;
import org.example.granafacil.core.application.gateways.PluggyGateway;
import org.example.granafacil.core.application.gateways.SincronizarContaGateway;
import org.example.granafacil.core.application.gateways.TransacaoGateway;
import org.example.granafacil.core.domain.entities.SincronizacaoConta;
import org.example.granafacil.core.domain.entities.Transacao;
import org.example.granafacil.core.domain.enums.StatusSincronizacao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class ExecutarSincronizacaoUseCase {

    private final PluggyGateway pluggyGateway;
    private final SincronizarContaGateway sincronizarContaGateway;
    private final TransacaoGateway transacaoGateway;

    public ExecutarSincronizacaoUseCase(
            PluggyGateway pluggyGateway,
            SincronizarContaGateway sincronizarContaGateway,
            TransacaoGateway transacaoGateway) {

        this.pluggyGateway = pluggyGateway;
        this.sincronizarContaGateway = sincronizarContaGateway;
        this.transacaoGateway = transacaoGateway;
    }

    public void executar(SincronizacaoConta sync) {

        log.info("Executando sincronizacao" + sync.getIdConta());

        LocalDateTime dataInicial = sync.getUltimaDataSync() != null
                ? sync.getUltimaDataSync()
                : LocalDateTime.now().minusMonths(2);

        int page = sync.getPaginaAtual() != null ? sync.getPaginaAtual() : 1;

        while (true) {

            TransactionsResponse resposta = pluggyGateway.listarTransacoes(
                    sync.getIdConta(),
                    page,
                    dataInicial
            );

            log.info("Resposta api" + resposta.toString());

            if (resposta == null || resposta.getResults().isEmpty()) {
                break;
            }

            upsertTransactionBatch(resposta, sync);

            page++;
            sync.setPaginaAtual(page);


            sincronizarContaGateway.save(sync);
        }

        sync.setStatusAtual(StatusSincronizacao.CONCLUIDO);
        sync.setPaginaAtual(1);
        sync.setUltimaDataSync(LocalDateTime.now());

        sincronizarContaGateway.save(sync);
    }

    private void upsertTransactionBatch(TransactionsResponse resposta, SincronizacaoConta sync) {

        List<Transacao> batchParaSalvar = new ArrayList<>();

        for (TransactionDTO result : resposta.getResults()) {

            Optional<Transacao> repo = transacaoGateway.findByContaIdAndPluggyId(sync.getIdConta(),result.getPluggyId());

            if (repo.isPresent()) {

                Transacao t = repo.get();

                t.setDescricao(result.getDescription());
                t.setDescricaoRaw(result.getDescriptionRaw());
                t.setCategoria(result.getCategory());
                t.setCategoriaId(result.getCategoryId());
                t.setTipo(result.getType());
                t.setStatus(result.getStatus());
                t.setMoeda(result.getCurrencyCode());
                t.setValor(result.getAmount());
                t.setData(result.getDate());
                t.setSaldo(result.getBalance());
                t.setDataAtualizacao(LocalDateTime.now());

                batchParaSalvar.add(t);

            } else {

                Transacao nova = new Transacao();

                nova.setPluggyId(result.getId());
                nova.setContaId(sync.getIdConta());
                nova.setDescricao(result.getDescription());
                nova.setDescricaoRaw(result.getDescriptionRaw());
                nova.setCategoria(result.getCategory());
                nova.setCategoriaId(result.getCategoryId());
                nova.setTipo(result.getType());
                nova.setStatus(result.getStatus());
                nova.setMoeda(result.getCurrencyCode());
                nova.setValor(result.getAmount());
                nova.setData(result.getDate());
                nova.setSaldo(result.getBalance());
                nova.setDataImportacao(LocalDateTime.now());
                nova.setDataAtualizacao(LocalDateTime.now());
                nova.setAtivo(true);

                batchParaSalvar.add(nova);
            }
        }

        // <<<<<< SALVA TUDO DE UMA VEZ >>>>>
        transacaoGateway.saveAll(batchParaSalvar);
    }
}