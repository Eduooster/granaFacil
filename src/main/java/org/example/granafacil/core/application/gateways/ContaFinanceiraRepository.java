package org.example.granafacil.core.application.gateways;

import org.example.granafacil.core.domain.entities.ContaFinanceira;

import java.util.List;

public interface ContaFinanceiraRepository {



    void saveAll(List<ContaFinanceira> contas);

    void save(ContaFinanceira conta);
}
