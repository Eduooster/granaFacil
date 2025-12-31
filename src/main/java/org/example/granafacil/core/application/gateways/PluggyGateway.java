package org.example.granafacil.core.application.gateways;


import org.example.granafacil.core.application.dtos.PluggyDtos.PluggyItemAccounts;
import org.example.granafacil.core.application.dtos.TransacaoDtos.TransactionsResponse;

import java.time.Instant;
import java.util.List;

public interface PluggyGateway {
    String gerarApiKey();
    String getConnectToken();
    List<PluggyItemAccounts> getItemsAccount(String itemId);
    TransactionsResponse listarTransacoes(String accountId, Integer page, Instant from,Instant to);





}
