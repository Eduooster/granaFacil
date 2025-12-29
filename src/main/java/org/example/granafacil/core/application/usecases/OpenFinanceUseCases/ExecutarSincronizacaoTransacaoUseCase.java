package org.example.granafacil.core.application.usecases.OpenFinanceUseCases;

import jakarta.transaction.Transactional;
import org.apache.logging.log4j.util.InternalException;
import org.example.granafacil.core.application.dtos.TransactionDTO;
import org.example.granafacil.core.application.dtos.TransactionsResponse;
import org.example.granafacil.core.application.gateways.ContaFinanceiraRepository;
import org.example.granafacil.core.application.gateways.PluggyGateway;
import org.example.granafacil.core.application.gateways.SincronizarContaRepository;
import org.example.granafacil.core.application.gateways.TransacaoRepository;
import org.example.granafacil.core.application.services.CategoriaService;
import org.example.granafacil.core.application.services.ClassificarMovimentacaoService;
import org.example.granafacil.core.domain.entities.ConexaoOpenFinance;
import org.example.granafacil.core.domain.entities.ContaFinanceira;
import org.example.granafacil.core.domain.entities.SincronizacaoConta;
import org.example.granafacil.core.domain.entities.Transacao;
import org.example.granafacil.core.domain.enums.OrigemTransacao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Transactional
public class ExecutarSincronizacaoTransacaoUseCase {

    private static final Logger log = LoggerFactory.getLogger(ExecutarSincronizacaoTransacaoUseCase.class);
    private PluggyGateway pluggyGateway;

    private final ContaFinanceiraRepository contaFinanceiraRepository;
    private final TransacaoRepository transacaoRepository;
    private final CategoriaService categoriaService;
    private final ClassificarMovimentacaoService classificarMovimentacaoService;

    public ExecutarSincronizacaoTransacaoUseCase(PluggyGateway pluggygateway, ContaFinanceiraRepository contaFinanceiraRepository, TransacaoRepository transacaoRepository, CategoriaService categoriaService, ClassificarMovimentacaoService classificarMovimentacaoService) {

        this.pluggyGateway = pluggygateway;

        this.contaFinanceiraRepository = contaFinanceiraRepository;

        this.transacaoRepository = transacaoRepository;
        this.categoriaService = categoriaService;
        this.classificarMovimentacaoService = classificarMovimentacaoService;
    }

    public void execute(SincronizacaoConta sync) {


        Instant dataInicial = sync.getUltimaDataSync() != null
                ? sync.getUltimaDataSync()
                : Instant.now().minus(60, ChronoUnit.DAYS);

        Instant dataFinal   = Instant.now();

        int page = 1;

        ContaFinanceira contaGerenciada =
                contaFinanceiraRepository
                        .findByExternalAccountId(sync.getConta().getExternalAccountId())
                        .orElseThrow(() -> new InternalException("Conta gerenciada inexistente"));

        while (true) {

            TransactionsResponse resposta =
                    pluggyGateway.listarTransacoes(
                            contaGerenciada.getExternalAccountId(),
                            page,
                            dataInicial,
                            dataFinal
                    );



            if (resposta == null || resposta.getResults().isEmpty()) {
                break;
            }

            upsertTransactionBatch(resposta, contaGerenciada);

            page++;
        }
    }


    private void upsertTransactionBatch(
            TransactionsResponse resposta,
            ContaFinanceira contaGerenciada
    ) {




        List<Transacao> batchParaSalvar = new ArrayList<>();


        for (TransactionDTO dto : resposta.getResults()) {



            log.info("procurando pela transacao: {}", dto);
            Optional<Transacao> existente =
                    transacaoRepository.findByContaIdAndExternalTransactionId(
                            contaGerenciada.getId(),
                            dto.getId()
                    );


            Transacao transacao = existente.orElseGet(Transacao::new);

            transacao.setExternalTransactionId(dto.getId());
            transacao.setContaId(contaGerenciada.getId());
            transacao.setOrigemTransacao(OrigemTransacao.OPEN_FINANCE);
            transacao.setCategoria(dto.getCategory() != null ? dto.getCategory() : "OUTROS");
            transacao.setCategoriaId(dto.getCategoryId() != null ? dto.getCategoryId() : "0");

            // 4. Dados mutáveis
            transacao.setDescricao(dto.getDescription());
            transacao.setDescricaoRaw(dto.getDescriptionRaw());

            transacao.setTipo(dto.getType());
            transacao.setStatus(dto.getStatus());
            transacao.setMoeda(dto.getCurrencyCode());
            transacao.setValor(dto.getAmount());

            transacao.setCategoriaInterna(
                    categoriaService.pluggyToInterna.get(dto.getCategoryId()));

            transacao.setTipoMovimentacao(classificarMovimentacaoService.classificar(dto.getAmount(),dto.getCategoryId()));

            transacao.setAtivo(true);


            Instant dataTransacao = dto.getDate()
                    .atZone(ZoneId.of("America/Sao_Paulo"))
                    .toInstant();

            transacao.setData(dataTransacao);
            transacao.setDataAtualizacao(Instant.now());

            if (existente.isEmpty()) {
                transacao.setDataImportacao(Instant.now());
            }

            batchParaSalvar.add(transacao);
        }

        log.info("batch para salvar: {}", batchParaSalvar);

        if (batchParaSalvar.isEmpty()) {

            return;
        }


        transacaoRepository.saveAll(batchParaSalvar);
    }
}
