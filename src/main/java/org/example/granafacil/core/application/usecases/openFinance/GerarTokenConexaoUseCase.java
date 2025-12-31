package org.example.granafacil.core.application.usecases.openFinance;

import org.example.granafacil.core.application.gateways.PluggyGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GerarTokenConexaoUseCase {
    private static final Logger log = LoggerFactory.getLogger(GerarTokenConexaoUseCase.class);
    private final PluggyGateway pluggyGateway;
    private final GerarAutenticacaoOpenFinanceUseCase authUseCase;

    public GerarTokenConexaoUseCase(PluggyGateway pluggyGateway, GerarAutenticacaoOpenFinanceUseCase authUseCase) {
        this.pluggyGateway = pluggyGateway;
        this.authUseCase = authUseCase;
    }

    public String execute() {
        return pluggyGateway.getConnectToken();
    }
}
