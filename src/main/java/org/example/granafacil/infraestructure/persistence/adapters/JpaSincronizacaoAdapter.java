package org.example.granafacil.infraestructure.persistence.adapters;

import org.example.granafacil.core.application.gateways.SincronizarContaRepository;
import org.example.granafacil.core.domain.entities.SincronizacaoConta;
import org.example.granafacil.core.domain.enums.StatusSincronizacao;
import org.example.granafacil.core.domain.enums.TipoSincronizacao;
import org.example.granafacil.infraestructure.persistence.entites.SincronizacaoContaEntity;
import org.example.granafacil.infraestructure.persistence.mapper.ContaFinanceiraMapper;
import org.example.granafacil.infraestructure.persistence.mapper.SincronizacaoContaMapper;
import org.example.granafacil.infraestructure.persistence.repositories.SincronizacaoContaRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaSincronizacaoAdapter implements SincronizarContaRepository {

    private static final Logger log = LoggerFactory.getLogger(JpaSincronizacaoAdapter.class);
    private final SincronizacaoContaRepositoryImpl contaRepository;
    private final SincronizacaoContaMapper mapper;
    private final SincronizacaoContaRepositoryImpl sincronizacaoContaRepositoryImpl;
    private final SincronizacaoContaMapper sincronizacaoContaMapper;
    private final ContaFinanceiraMapper contaFinanceiraMapper;

    public JpaSincronizacaoAdapter(SincronizacaoContaRepositoryImpl contaRepository, SincronizacaoContaMapper mapper, SincronizacaoContaRepositoryImpl sincronizacaoContaRepositoryImpl, SincronizacaoContaMapper sincronizacaoContaMapper, ContaFinanceiraMapper contaFinanceiraMapper) {
        this.contaRepository = contaRepository;
        this.mapper = mapper;
        this.sincronizacaoContaRepositoryImpl = sincronizacaoContaRepositoryImpl;
        this.sincronizacaoContaMapper = sincronizacaoContaMapper;
        this.contaFinanceiraMapper = contaFinanceiraMapper;
    }


    @Override
    public SincronizacaoConta findByIdConta(String id) {
        return null;
    }

    @Override
    public SincronizacaoConta save(SincronizacaoConta conta) {

        SincronizacaoContaEntity sincronizacaoContaEntity = mapper.toEntity(conta);




        return  mapper.toDomain(contaRepository.save(sincronizacaoContaEntity));
    }

    @Override
    public List<SincronizacaoConta> buscarContasParaSincronizar(StatusSincronizacao atual, TipoSincronizacao tipoSincronizacao) {
        List<SincronizacaoContaEntity> entity=  sincronizacaoContaRepositoryImpl.findConnectorIdsByStatus(atual);

        return entity.stream().map(e -> sincronizacaoContaMapper.toDomain(e)).toList();
    }



    @Override
    public List<SincronizacaoConta> buscarContasParaSincronizarPorConexao(StatusSincronizacao statusSincronizacao,  TipoSincronizacao tipoSincronizacao) {
        List<SincronizacaoContaEntity> entity=
                sincronizacaoContaRepositoryImpl.buscarPorStatusETipo(statusSincronizacao,tipoSincronizacao);
        log.info("lista entity de sync" + entity);

        return entity.stream().map(e -> sincronizacaoContaMapper.toDomain(e)).toList();
    }

    @Override
    public SincronizacaoConta buscarPorContaETipo(Long contaId, TipoSincronizacao tipoSincronizacao) {
        return sincronizacaoContaMapper.toDomain(sincronizacaoContaRepositoryImpl.findByContaIdAndTipo(contaId,tipoSincronizacao));
    }
}
