package org.example.granafacil.infraestructure.persistence.adapters;

import org.example.granafacil.core.application.gateways.TransacaoRepository;
import org.example.granafacil.core.domain.entities.Transacao;
import org.example.granafacil.infraestructure.persistence.entites.TransacaoEntity;
import org.example.granafacil.infraestructure.persistence.mapper.TransacaoMapper;
import org.example.granafacil.infraestructure.persistence.repositories.TransacaoRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaTransacaoAdapter implements TransacaoRepository {
    private static final Logger log = LoggerFactory.getLogger(JpaTransacaoAdapter.class);
    private final TransacaoRepositoryImpl transacaoRepositoryImpl;
    private final TransacaoMapper transacaoMapper;

    public JpaTransacaoAdapter(TransacaoRepositoryImpl transacaoRepositoryImpl, TransacaoMapper transacaoMapper) {
        this.transacaoRepositoryImpl = transacaoRepositoryImpl;
        this.transacaoMapper = transacaoMapper;
    }

    @Override
    public Optional<Transacao> findByContaIdAndPluggyId(String contaId, String pluggyId) {

        var entity = transacaoRepositoryImpl.findByContaIdAndPluggyId(contaId, pluggyId);

        return Optional.ofNullable(entity)
                .map(transacaoMapper::toDomain);
    }

    @Override
    public Transacao save(Transacao t) {


        return null;
    }

    @Override
    public void saveAll(List<Transacao> transacoes) {
        List<TransacaoEntity> entity = transacaoMapper.toEntityList(transacoes);
        transacaoRepositoryImpl.saveAll(entity);

    }
}
