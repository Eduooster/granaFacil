package org.example.granafacil.infraestructure.integrations.pluggy;

import org.example.granafacil.core.application.dtos.PluggyItemAccounts;
import org.example.granafacil.core.application.dtos.TransactionsResponse;
import org.example.granafacil.core.application.gateways.PluggyGateway;
import org.example.granafacil.infraestructure.presentation.dto.pluggyDto.AcessTokenResponse;
import org.example.granafacil.infraestructure.presentation.dto.pluggyDto.AuthRequest;
import org.example.granafacil.infraestructure.presentation.dto.pluggyDto.AuthResponse;
import org.example.granafacil.infraestructure.presentation.dto.pluggyDto.PluggyAccountsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Configuration
public class PluggyAdapter implements PluggyGateway {

    private final WebClient webClient;
    private final String clientId = System.getenv("CLIENT_ID_PLUGGY");
    private final String clientSecret = System.getenv("CLIENT_SECRET_PLUGGY");

    private static final Logger log = LoggerFactory.getLogger(PluggyAdapter.class);

    public PluggyAdapter(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public String gerarApiKey() {
        AuthRequest body = new AuthRequest(clientId, clientSecret);

        log.info(body.toString());

        AuthResponse response = webClient.post()
                .uri("/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(AuthResponse.class)
                .block();

        return response.apiKey();
    }

    @Override
    public String getConnectToken() {
        String apiKey = gerarApiKey();

        log.info(apiKey);


        AcessTokenResponse response = webClient.post()
                .uri("/connect_token")
                .header("x-api-key", apiKey)
                .retrieve()
                .bodyToMono(AcessTokenResponse.class)
                .block();

        return response.accessToken();
    }

    @Override
    public List<PluggyItemAccounts> getItemsAccount(String itemId) {

        String apiKey = gerarApiKey();

        log.info(apiKey);

        PluggyAccountsResponse response = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/accounts")
                            .queryParam("itemId", itemId)
                            .build())
                    .header("x-api-key", apiKey)
                    .retrieve()
                    .bodyToMono(PluggyAccountsResponse.class)
                    .block();

            log.info(response.toString());

            return response.getResults();


    }

    @Override
    public TransactionsResponse listarTransacoes(String accountId, Integer page, LocalDateTime from) {
        String apiKey = gerarApiKey();

        return webClient.get()
                .uri(uriBuilder -> {
                    var builder = uriBuilder
                            .path("/transactions")
                            .queryParam("accountId", accountId);


                    if (page != null && page >= 0) {
                        builder.queryParam("page", page);
                    }


                    if (from != null) {
                        String fromIso = from
                                .atZone(ZoneId.systemDefault())
                                .withZoneSameInstant(ZoneOffset.UTC)
                                .format(DateTimeFormatter.ISO_INSTANT);

                        builder.queryParam("from", fromIso);
                    }

                    return builder.build();
                })
                .header("x-api-key", apiKey)
                .retrieve()
                .bodyToMono(TransactionsResponse.class)
                .block();
    }






}
