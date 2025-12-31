package org.example.granafacil.infraestructure.persistence.adapters;

import org.example.granafacil.core.application.dtos.ContaFinanceiraDtos.ContaFinanceiraResponse;
import org.example.granafacil.core.application.gateways.ContaFinanceiraRepository;
import org.example.granafacil.core.domain.entities.ContaFinanceira;
import org.example.granafacil.infraestructure.persistence.entites.ContaFinanceiraEntity;
import org.example.granafacil.infraestructure.persistence.mapper.ContaFinanceiraMapper;
import org.example.granafacil.infraestructure.persistence.mapper.UsuarioEntityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JpaContaFinanceiraAdapter implements ContaFinanceiraRepository {
    private static final Logger log = LoggerFactory.getLogger(JpaContaFinanceiraAdapter.class);
    private final org.example.granafacil.infraestructure.persistence.repositories.ContaFinanceiraRepository contaFinanceiraRepository;

    private final ContaFinanceiraMapper contaFinanceiraMapper;
    private final JpaUsuarioAdapter jpaUsuarioAdapter;
    private final UsuarioEntityMapper usuarioEntityMapper;


    public JpaContaFinanceiraAdapter(org.example.granafacil.infraestructure.persistence.repositories.ContaFinanceiraRepository contaFinanceiraRepository, ContaFinanceiraMapper contaFinanceiraMapper, JpaUsuarioAdapter jpaUsuarioAdapter, UsuarioEntityMapper usuarioEntityMapper) {
        this.contaFinanceiraRepository = contaFinanceiraRepository;
        this.contaFinanceiraMapper = contaFinanceiraMapper;
        this.jpaUsuarioAdapter = jpaUsuarioAdapter;
        this.usuarioEntityMapper = usuarioEntityMapper;
    }


    @Override
    public List<ContaFinanceiraResponse> findAllAccountByUsuarioIdToResponse(Long usuarioId) {
        return contaFinanceiraRepository.findContasByUsuarioIdResponse(usuarioId);
    }

    @Override
    public List<ContaFinanceira> findAllAccountByUsuarioId(Long usuarioId) {
        return contaFinanceiraRepository.findContasByUsuarioId(usuarioId);
    }

    @Override
    public void saveAll(List<ContaFinanceira> contas) {
        log.info("domain"+ contas.toString());

        contas.forEach(conta -> {
            var entity = contaFinanceiraMapper.toEntity(conta);
            log.info("entity"+ entity.toString());
            contaFinanceiraRepository.save(entity);

        });

    }

    @Override
    public ContaFinanceira save(ContaFinanceira conta) {
        var entity = contaFinanceiraMapper.toEntity(conta);


        if (conta.getUsuario() != null && conta.getUsuario().getId() != null) {

            var usuarioRef = jpaUsuarioAdapter.buscaIdUsuario(conta.getUsuario().getId());

            entity.setUsuario(usuarioEntityMapper.toEntity(usuarioRef));
        }

        ContaFinanceiraEntity contaRepo =  contaFinanceiraRepository.save(entity);

        return contaFinanceiraMapper.toDomain(contaRepo);

    }

    @Override
    public Optional<ContaFinanceira> findByExternalAccountId(String externalAccountId) {
        return contaFinanceiraRepository
                .findByExternalAccountId(externalAccountId)
                .map(contaFinanceiraMapper::toDomain);




    }

    @Override
    public List<ContaFinanceira> findByConexaoOpenFinanceId(Long id) {
        return contaFinanceiraRepository.findByConexaoOpenFinanceId(id)
                .stream()
                .map(contaFinanceiraMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsByUsuarioIdAndIdConnectorAndAccountNumber(Long usuarioId, Long connectorId, String number) {
        return contaFinanceiraRepository.existsByUsuarioIdAndInstituicaoFinanceiraIdConnectorAndNumber(usuarioId,connectorId,number);
    }


}
