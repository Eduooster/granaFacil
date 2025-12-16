package org.example.granafacil.infraestructure.persistence.adapters;

import org.example.granafacil.core.application.gateways.SincronizarContaRepository;
import org.example.granafacil.core.domain.entities.SincronizacaoConta;
import org.example.granafacil.core.domain.enums.StatusSincronizacao;
import org.example.granafacil.infraestructure.persistence.entites.SincronizacaoContaEntity;
import org.example.granafacil.infraestructure.persistence.mapper.SincronizacaoContaMapper;
import org.example.granafacil.infraestructure.persistence.repositories.SincronizacaoContaRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaSincronizacaoAdapter implements SincronizarContaRepository {

    private static final Logger log = LoggerFactory.getLogger(JpaSincronizacaoAdapter.class);
    private final SincronizacaoContaRepositoryImpl contaRepository;
    private final SincronizacaoContaMapper mapper;
    private final SincronizacaoContaRepositoryImpl sincronizacaoContaRepositoryImpl;
    private final SincronizacaoContaMapper sincronizacaoContaMapper;

    public JpaSincronizacaoAdapter(SincronizacaoContaRepositoryImpl contaRepository, SincronizacaoContaMapper mapper, SincronizacaoContaRepositoryImpl sincronizacaoContaRepositoryImpl, SincronizacaoContaMapper sincronizacaoContaMapper) {
        this.contaRepository = contaRepository;
        this.mapper = mapper;
        this.sincronizacaoContaRepositoryImpl = sincronizacaoContaRepositoryImpl;
        this.sincronizacaoContaMapper = sincronizacaoContaMapper;
    }


    @Override
    public SincronizacaoConta findByIdConta(String id) {
        return null;
    }

    @Override
    public void save(SincronizacaoConta conta) {
        log.info(conta.toString());
        SincronizacaoContaEntity sincronizacaoContaEntity = mapper.toEntity(conta);
        log.info("Entity sincronizado: {}", sincronizacaoContaEntity.toString());

        contaRepository.save(sincronizacaoContaEntity);
    }

    @Override
    public List<SincronizacaoConta> buscarContasParaSincronizar(StatusSincronizacao atual) {
        List<SincronizacaoContaEntity> entity=  sincronizacaoContaRepositoryImpl.findConnectorIdsByStatusAtual(atual);

        return entity.stream().map(e -> sincronizacaoContaMapper.toDomain(e)).toList();
    }
}
