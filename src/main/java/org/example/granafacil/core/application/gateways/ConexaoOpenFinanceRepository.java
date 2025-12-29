package org.example.granafacil.core.application.gateways;

import org.example.granafacil.core.domain.entities.ConexaoOpenFinance;

import java.util.Optional;

public interface ConexaoOpenFinanceRepository {

    ConexaoOpenFinance salvar(ConexaoOpenFinance conexao);



    Optional<ConexaoOpenFinance> findByPluggyItemIdAndAtivoTrue(String pluggyItemId);


    Optional<ConexaoOpenFinance> findById(Long id);

    void deletarItemId(String pluggyItemId);

    boolean existsByPluggyItemId(String pluggyItemId);
}
