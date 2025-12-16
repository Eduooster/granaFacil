package org.example.granafacil.infraestructure.persistence.adapters;

import org.example.granafacil.core.application.gateways.ContaFinanceiraRepository;
import org.example.granafacil.core.domain.entities.ContaFinanceira;
import org.example.granafacil.infraestructure.persistence.mapper.ContaFinanceiraMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JpaContaFinanceiraAdapter implements ContaFinanceiraRepository {
    private static final Logger log = LoggerFactory.getLogger(JpaContaFinanceiraAdapter.class);
    private final org.example.granafacil.infraestructure.persistence.repositories.ContaFinanceiraRepository contaFinanceiraRepository;

    private final ContaFinanceiraMapper contaFinanceiraMapper;


    public JpaContaFinanceiraAdapter(org.example.granafacil.infraestructure.persistence.repositories.ContaFinanceiraRepository contaFinanceiraRepository, ContaFinanceiraMapper contaFinanceiraMapper) {
        this.contaFinanceiraRepository = contaFinanceiraRepository;
        this.contaFinanceiraMapper = contaFinanceiraMapper;
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
    public void save(ContaFinanceira conta) {

    }
}
