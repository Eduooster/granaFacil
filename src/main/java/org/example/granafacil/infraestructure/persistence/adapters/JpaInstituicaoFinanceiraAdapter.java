package org.example.granafacil.infraestructure.persistence.adapters;

import org.example.granafacil.core.application.gateways.InstituicaoFinanceiraGateway;
import org.example.granafacil.core.domain.entities.InstituicaoFinanceira;
import org.example.granafacil.infraestructure.persistence.entites.InstituicaoFinanceiraEntity;
import org.example.granafacil.infraestructure.persistence.mapper.InstituicaEntityMapper;
import org.example.granafacil.infraestructure.persistence.repositories.InstituicaoFinanceiraRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;



@Component
public class JpaInstituicaoFinanceiraAdapter implements InstituicaoFinanceiraGateway {

    private final InstituicaoFinanceiraRepository instituicaoFinanceiraRepository;
    private final InstituicaEntityMapper instituicaEntityMapper;
    private static final Logger log = LoggerFactory.getLogger(JpaInstituicaoFinanceiraAdapter.class);

    public JpaInstituicaoFinanceiraAdapter(InstituicaoFinanceiraRepository instituicaoFinanceiraRepository, InstituicaEntityMapper instituicaEntityMapper) {
        this.instituicaoFinanceiraRepository = instituicaoFinanceiraRepository;
        this.instituicaEntityMapper = instituicaEntityMapper;
    }

    @Override
    public InstituicaoFinanceira buscarPorId(Long id) {
        Optional<InstituicaoFinanceira> instituicaoFinanceiraEntity =
                instituicaoFinanceiraRepository.findById(id).map(ie-> instituicaEntityMapper.toDomain(ie));


        if (instituicaoFinanceiraEntity.isPresent()) {

            return instituicaoFinanceiraEntity.get();
        }
        return null;
    }

    @Override
    public InstituicaoFinanceira salvar(InstituicaoFinanceira instituicaoFinanceira) {

        log.info("teste");

         InstituicaoFinanceiraEntity entity =  instituicaoFinanceiraRepository.save(instituicaEntityMapper.toEntity(instituicaoFinanceira));

         return instituicaEntityMapper.toDomain(entity);
    }

    @Override
    public Optional<InstituicaoFinanceira> buscarPorIdConnector(Long connectorId) {
        Optional <InstituicaoFinanceira> instituicaoFinanceira = instituicaoFinanceiraRepository.
                findByIdConnector(connectorId)
                .map(i-> instituicaEntityMapper.toDomain(i));
        if (instituicaoFinanceira.isPresent()) {
            return instituicaoFinanceira;
        }
        return Optional.empty();
    }
}
