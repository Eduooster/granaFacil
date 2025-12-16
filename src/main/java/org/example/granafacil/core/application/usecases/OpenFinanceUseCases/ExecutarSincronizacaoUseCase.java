package org.example.granafacil.core.application.usecases.OpenFinanceUseCases;

import lombok.extern.slf4j.Slf4j;
import org.example.granafacil.core.application.dtos.TransactionDTO;
import org.example.granafacil.core.application.dtos.TransactionsResponse;
import org.example.granafacil.core.application.gateways.PluggyGateway;
import org.example.granafacil.core.application.gateways.SincronizarContaRepository;
import org.example.granafacil.core.application.gateways.TransacaoRepository;
import org.example.granafacil.core.application.services.CategoriaService;
import org.example.granafacil.core.domain.entities.SincronizacaoConta;
import org.example.granafacil.core.domain.entities.Transacao;
import org.example.granafacil.core.domain.enums.CategoriaInterna;
import org.example.granafacil.core.domain.enums.StatusSincronizacao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ExecutarSincronizacaoUseCase {

    private static final Logger log = LoggerFactory.getLogger(ExecutarSincronizacaoUseCase.class);
    private final PluggyGateway pluggyGateway;
    private final SincronizarContaRepository sincronizarContaRepository;
    private final TransacaoRepository transacaoRepository;


    public ExecutarSincronizacaoUseCase(
            PluggyGateway pluggyGateway,
            SincronizarContaRepository sincronizarContaRepository,
            TransacaoRepository transacaoRepository
    )

    {

        this.pluggyGateway = pluggyGateway;
        this.sincronizarContaRepository = sincronizarContaRepository;
        this.transacaoRepository = transacaoRepository;


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


            sincronizarContaRepository.save(sync);
        }

        sync.setStatusAtual(StatusSincronizacao.CONCLUIDO);
        sync.setPaginaAtual(1);
        sync.setUltimaDataSync(LocalDateTime.now());

        sincronizarContaRepository.save(sync);
    }

    private void upsertTransactionBatch(TransactionsResponse resposta, SincronizacaoConta sync) {

        List<Transacao> batchParaSalvar = new ArrayList<>();
        CategoriaService categoriaService = new CategoriaService();

        for (TransactionDTO result : resposta.getResults()) {

            Optional<Transacao> repo = transacaoRepository.findByContaIdAndPluggyId(sync.getIdConta(),result.getPluggyId());

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
                t.setCategoriaInterna(categoriaService.pluggyToInterna.get(result.getPluggyId()));

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
                nova.setCategoriaInterna(categoriaService.pluggyToInterna.get(result.getPluggyId()));
                nova.setAtivo(true);

                log.info("Cadasntrando nova transacao"+ nova);

                batchParaSalvar.add(nova);
            }
        }

        log.info("batch para salvar" + batchParaSalvar);
        transacaoRepository.saveAll(batchParaSalvar);
    }
}