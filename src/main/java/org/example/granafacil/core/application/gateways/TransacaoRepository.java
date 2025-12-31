package org.example.granafacil.core.application.gateways;

import org.example.granafacil.infraestructure.persistence.projections.FinanceiroResumoProjection;
import org.example.granafacil.infraestructure.persistence.projections.MovimentacaoProjection;
import org.example.granafacil.core.domain.entities.Transacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface TransacaoRepository {



    Transacao save(Transacao transacao);

    void saveAll(List<Transacao> transacoes);

    Optional<Transacao> findByContaIdAndExternalTransactionId(Long contaId, String transactionId);

    FinanceiroResumoProjection calcularResumoFinanceiro(Long usuarioId,
                                                        Instant inicio,
                                                        Instant fim);

    Page<MovimentacaoProjection> buscarMovimentacoesRecentes(Long usuarioId,
                                                             Instant inicio,
                                                             Instant fim, Pageable pageable);




}
