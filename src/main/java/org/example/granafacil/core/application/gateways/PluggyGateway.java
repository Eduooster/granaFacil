package org.example.granafacil.core.application.gateways;


import org.example.granafacil.core.application.dtos.PluggyItemAccounts;
import org.example.granafacil.core.application.dtos.TransactionsResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface PluggyGateway {
    String gerarApiKey();
    String getConnectToken();
    List<PluggyItemAccounts> getItemsAccount(String itemId);
    TransactionsResponse listarTransacoes(String accountId, Integer page,LocalDateTime from);





}
