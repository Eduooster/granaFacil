package org.example.granafacil.core.application.usecases.OpenFinanceUseCases;

import org.example.granafacil.core.application.gateways.PluggyGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;

public class PluggyAuthUseCase {
    private static final Logger log = LoggerFactory.getLogger(PluggyAuthUseCase.class);
    private final PluggyGateway pluggyGateway;
    private String apiKey;
    private Instant expiration =Instant.MIN;

    public PluggyAuthUseCase(PluggyGateway pluggyGateway) {
        this.pluggyGateway = pluggyGateway;
    }

    public synchronized String getApiKey() {
        if (apiKey == null || Instant.now().isAfter(expiration)) {
            String authMono = pluggyGateway.gerarApiKey();

            this.apiKey = authMono;
            this.expiration = Instant.now().plus(Duration.ofHours(2));
        }
        log.info("apiKey pluggyauth: " + apiKey);
        return apiKey;
    }
    public synchronized void forceRefresh() {

        String freshApiKey = pluggyGateway.gerarApiKey();
        this.apiKey = freshApiKey;
        this.expiration = Instant.now().plus(Duration.ofHours(2));
    }
}
