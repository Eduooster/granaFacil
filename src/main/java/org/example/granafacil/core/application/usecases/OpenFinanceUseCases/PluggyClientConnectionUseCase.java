package org.example.granafacil.core.application.usecases.OpenFinanceUseCases;

import org.example.granafacil.core.application.gateways.PluggyGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PluggyClientConnectionUseCase {
    private static final Logger log = LoggerFactory.getLogger(PluggyClientConnectionUseCase.class);
    private final PluggyGateway pluggyGateway;
    private final PluggyAuthUseCase authUseCase;

    public PluggyClientConnectionUseCase(PluggyGateway pluggyGateway, PluggyAuthUseCase authUseCase) {
        this.pluggyGateway = pluggyGateway;
        this.authUseCase = authUseCase;
    }

    public String execute() {
        return pluggyGateway.getConnectToken();
    }
}
