package org.example.granafacil.core.application.gateways;

import org.example.granafacil.core.domain.entities.Transacao;

import java.util.List;
import java.util.Optional;

public interface TransacaoRepository {



    Transacao save(Transacao transacao);

    void saveAll(List<Transacao> transacoes);

    Optional<Transacao> findByContaIdAndExternalTransactionId(Long contaId, String transactionId);


}
