package org.example.granafacil.infraestructure.persistence.adapters;

import org.example.granafacil.core.application.gateways.SincronizarContaGateway;
import org.example.granafacil.core.application.usecases.OpenFinanceUseCases.PluggyClientItemUseCase;
import org.example.granafacil.core.domain.entities.SincronizacaoConta;
import org.example.granafacil.core.domain.enums.StatusSincronizacao;
import org.example.granafacil.infraestructure.persistence.entites.SincronizacaoContaEntity;
import org.example.granafacil.infraestructure.persistence.mapper.SincronizacaoContaMapper;
import org.example.granafacil.infraestructure.persistence.repositories.SincronizacaoContaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JpaSincronizacaoAdapter implements SincronizarContaGateway{

    private static final Logger log = LoggerFactory.getLogger(JpaSincronizacaoAdapter.class);
    private final SincronizacaoContaRepository contaRepository;
    private final SincronizacaoContaMapper mapper;
    private final SincronizacaoContaRepository sincronizacaoContaRepository;
    private final SincronizacaoContaMapper sincronizacaoContaMapper;

    public JpaSincronizacaoAdapter(SincronizacaoContaRepository contaRepository, SincronizacaoContaMapper mapper, SincronizacaoContaRepository sincronizacaoContaRepository, SincronizacaoContaMapper sincronizacaoContaMapper) {
        this.contaRepository = contaRepository;
        this.mapper = mapper;
        this.sincronizacaoContaRepository = sincronizacaoContaRepository;
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
        List<SincronizacaoContaEntity> entity=  sincronizacaoContaRepository.findConnectorIdsByStatusAtual(atual);

        return entity.stream().map(e -> sincronizacaoContaMapper.toDomain(e)).toList();
    }
}
