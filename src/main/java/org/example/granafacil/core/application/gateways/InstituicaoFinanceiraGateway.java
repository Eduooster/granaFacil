package org.example.granafacil.core.application.gateways;

import org.example.granafacil.core.domain.entities.InstituicaoFinanceira;

import java.util.Optional;

public interface InstituicaoFinanceiraGateway {

    InstituicaoFinanceira buscarPorId(Long id);

    InstituicaoFinanceira salvar(InstituicaoFinanceira instituicaoFinanceira);

    Optional<InstituicaoFinanceira> buscarPorIdConnector(Long connectorId);
}
