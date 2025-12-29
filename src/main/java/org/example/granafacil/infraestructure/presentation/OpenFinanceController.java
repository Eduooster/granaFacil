package org.example.granafacil.infraestructure.presentation;

import jakarta.websocket.server.PathParam;
import org.example.granafacil.core.application.gateways.SincronizarContaRepository;
import org.example.granafacil.core.application.orchestrator.OpenFinanceOrchestrator;
import org.example.granafacil.core.application.usecases.OpenFinanceUseCases.GerarTokenConexaoUseCase;
import org.example.granafacil.core.application.usecases.OpenFinanceUseCases.CriarItemConexaoUseCase;
import org.example.granafacil.core.application.usecases.OpenFinanceUseCases.SincronizarTransacoesConexaoUseCase;

import org.example.granafacil.core.domain.entities.Usuario;
import org.example.granafacil.infraestructure.persistence.entites.UsuarioEntity;
import org.example.granafacil.infraestructure.persistence.mapper.UsuarioEntityMapper;
import org.example.granafacil.infraestructure.presentation.dto.pluggyDto.AcessTokenResponse;
import org.example.granafacil.infraestructure.presentation.dto.pluggyDto.CreateItemDto;
import org.example.granafacil.infraestructure.presentation.mapper.PluggyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/open-finance")
@RestController
public class OpenFinanceController {
    private static final Logger log = LoggerFactory.getLogger(OpenFinanceController.class);


    private final GerarTokenConexaoUseCase gerarTokenConexaoUseCase;
    private final PluggyMapper pluggyMapper;
    private final OpenFinanceOrchestrator openFinanceOrchestrator;
    private final SincronizarTransacoesConexaoUseCase sincronizarTransacoesConexaoUseCase;

    public OpenFinanceController(GerarTokenConexaoUseCase gerarTokenConexaoUseCase, PluggyMapper pluggyMapper, OpenFinanceOrchestrator openFinanceOrchestrator, SincronizarTransacoesConexaoUseCase sincronizarTransacoesConexaoUseCase) {
        this.gerarTokenConexaoUseCase = gerarTokenConexaoUseCase;
        this.pluggyMapper = pluggyMapper;
        this.openFinanceOrchestrator = openFinanceOrchestrator;
        this.sincronizarTransacoesConexaoUseCase = sincronizarTransacoesConexaoUseCase;
    }

    @GetMapping
    public ResponseEntity< AcessTokenResponse> getAcessToken() {
        String acessToken = gerarTokenConexaoUseCase.execute();
        return ResponseEntity.ok(new AcessTokenResponse(acessToken));
    }

    @PostMapping("/item")
    public ResponseEntity getPluggyItem(@AuthenticationPrincipal UsuarioEntity usuario,@RequestBody CreateItemDto createItemDto){

        var domain = pluggyMapper.toDomain(createItemDto);
        domain.setUsuarioId(usuario.getId());
        openFinanceOrchestrator.criarConexaoEImportarContas(domain);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/conexoes/{conexaoId}/sync-transacoes")
    public ResponseEntity sync(@AuthenticationPrincipal UsuarioEntity usuario, @PathVariable("conexaoId") String conexaoId) {

        log.info("conexao id: {}", conexaoId);
        sincronizarTransacoesConexaoUseCase.execute(conexaoId);

        return ResponseEntity.ok().build();
    }








}
