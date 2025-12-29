package org.example.granafacil.core.application.gateways;

import org.example.granafacil.core.application.dtos.ContaFinanceiraResponse;
import org.example.granafacil.core.application.dtos.PluggyItemAccounts;
import org.example.granafacil.core.domain.entities.ContaFinanceira;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface ContaFinanceiraRepository {

    List<ContaFinanceiraResponse> findAllAccountByUsuarioIdToResponse(Long usuarioId);

    List<ContaFinanceira> findAllAccountByUsuarioId(Long usuarioId);

    void saveAll(List<ContaFinanceira> contas);

    ContaFinanceira save(ContaFinanceira conta);

    Optional<ContaFinanceira> findByExternalAccountId(String externalAccountId);



    List<ContaFinanceira> findByConexaoOpenFinanceId(Long id);

    boolean existsByUsuarioIdAndIdConnectorAndAccountNumber(Long usuarioId, Long connectorId, String number);
}
