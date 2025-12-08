package org.example.granafacil.infraestructure.persistence.adapters;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.granafacil.core.application.gateways.TransacaoGateway;
import org.example.granafacil.core.domain.entities.Transacao;
import org.example.granafacil.infraestructure.persistence.entites.TransacaoEntity;
import org.example.granafacil.infraestructure.persistence.repositories.TransacaoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaTransacaoAdapter implements TransacaoGateway {

    private final TransacaoRepository transacaoRepository;

    public JpaTransacaoAdapter(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    @Override
    public Optional<Transacao> findByContaIdAndPluggyId(String contaId, String pluggyId) {
        return transacaoRepository.findByContaIdAndPluggyId(contaId,pluggyId);


    }

    @Override
    public Transacao save(Transacao t) {
        return null;
    }

    @Override
    public void saveAll(List<Transacao> transacoes) {

    }
}
