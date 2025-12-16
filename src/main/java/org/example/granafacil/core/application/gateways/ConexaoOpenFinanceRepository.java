package org.example.granafacil.core.application.gateways;

import org.example.granafacil.core.domain.entities.ConexaoOpenFinance;

import java.util.Optional;

public interface ConexaoOpenFinanceRepository {

    ConexaoOpenFinance salvar(ConexaoOpenFinance conexao);

    Optional<ConexaoOpenFinance> buscarPorItemId(String pluggyItemId);

    Optional<ConexaoOpenFinance> findByPluggyItemIdAndAtivoTrue(String pluggyItemId);
}
