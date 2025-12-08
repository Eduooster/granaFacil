package org.example.granafacil.infraestructure.persistence.adapters;

import org.example.granafacil.core.application.gateways.ConexaoOpenFinanceGateway;
import org.example.granafacil.core.domain.entities.ConexaoOpenFinance;
import org.example.granafacil.infraestructure.persistence.mapper.ConexaoOpenFinanceEntityMapper;
import org.example.granafacil.infraestructure.persistence.repositories.ConexaoOpenFinanceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JpaOpenFinanceAdapter implements ConexaoOpenFinanceGateway {

    private static final Logger log = LoggerFactory.getLogger(JpaOpenFinanceAdapter.class);

    private final ConexaoOpenFinanceRepository conexaoOpenFinanceRepository;
    private final ConexaoOpenFinanceEntityMapper conexaoOpenFinanceEntityMapper;

    public JpaOpenFinanceAdapter(ConexaoOpenFinanceRepository conexaoOpenFinanceRepository, ConexaoOpenFinanceEntityMapper conexaoOpenFinanceEntityMapper) {
        this.conexaoOpenFinanceRepository = conexaoOpenFinanceRepository;
        this.conexaoOpenFinanceEntityMapper = conexaoOpenFinanceEntityMapper;
    }


    @Override
    public ConexaoOpenFinance salvar(ConexaoOpenFinance conexao) {

        var entity = conexaoOpenFinanceEntityMapper.toEntity(conexao);

         return conexaoOpenFinanceEntityMapper.toDomain(conexaoOpenFinanceRepository.save(entity));
    }

    @Override
    public Optional<ConexaoOpenFinance> buscarPorItemId(String pluggyItemId) {

        return conexaoOpenFinanceRepository.findByPluggyItemId(pluggyItemId)
                .map(cf -> conexaoOpenFinanceEntityMapper.toDomain(cf));
    }

    @Override
    public Optional<ConexaoOpenFinance> findByPluggyItemIdAndAtivoTrue(String pluggyItemId) {
        return conexaoOpenFinanceRepository
                .findByPluggyItemIdAndAtivoTrue(pluggyItemId)
                .map(cf-> conexaoOpenFinanceEntityMapper.toDomain(cf));
    }
}
