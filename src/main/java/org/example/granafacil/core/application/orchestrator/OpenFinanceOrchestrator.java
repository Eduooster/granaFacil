package org.example.granafacil.core.application.orchestrator;

import lombok.extern.slf4j.Slf4j;
import org.example.granafacil.core.application.dtos.PluggyDtos.PluggyItemData;
import org.example.granafacil.core.application.usecases.openFinance.CriarItemConexaoUseCase;
import org.example.granafacil.core.application.usecases.openFinance.ImportarContasUseCase;
import org.example.granafacil.core.domain.entities.ConexaoOpenFinance;

@Slf4j
public class OpenFinanceOrchestrator {

    private final ImportarContasUseCase importarContasUseCase;
    private final CriarItemConexaoUseCase criarItemConexaoUseCase;

    public OpenFinanceOrchestrator(ImportarContasUseCase importarContasUseCase, CriarItemConexaoUseCase criarItemConexaoUseCase) {
        this.importarContasUseCase = importarContasUseCase;
        this.criarItemConexaoUseCase = criarItemConexaoUseCase;
    }


    public void criarConexaoEImportarContas(PluggyItemData data) {
        log.trace("Entrando no orquestrador");
        ConexaoOpenFinance conexao = criarItemConexaoUseCase.execute(data);
        log.trace("Conexao criada com sucesso");
        importarContasUseCase.execute(conexao);
    }
}
