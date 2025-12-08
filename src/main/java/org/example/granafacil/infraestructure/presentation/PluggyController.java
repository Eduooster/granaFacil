package org.example.granafacil.infraestructure.presentation;

import org.example.granafacil.core.application.gateways.SincronizarContaGateway;
import org.example.granafacil.core.application.usecases.OpenFinanceUseCases.PluggyClientConnectionUseCase;
import org.example.granafacil.core.application.usecases.OpenFinanceUseCases.PluggyClientItemUseCase;
import org.example.granafacil.core.application.usecases.OpenFinanceUseCases.SincronizarTransacoesUseCase;
import org.example.granafacil.core.domain.entities.SincronizacaoConta;
import org.example.granafacil.infraestructure.persistence.entites.UsuarioEntity;
import org.example.granafacil.infraestructure.presentation.dto.pluggyDto.AcessTokenResponse;
import org.example.granafacil.infraestructure.presentation.dto.pluggyDto.CreateItemDto;
import org.example.granafacil.infraestructure.presentation.mapper.PluggyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/open-finance")
@RestController
public class PluggyController {
    private static final Logger log = LoggerFactory.getLogger(PluggyController.class);


    private final PluggyClientConnectionUseCase pluggyClientConnectionUseCase;
    private final PluggyClientItemUseCase pluggyClientItemUseCase;
    private final PluggyMapper pluggyMapper;
    private final SincronizarContaGateway sincronizarContaGateway;
    private final SincronizarTransacoesUseCase sincronizarTransacoesUseCase;

    public PluggyController(PluggyClientConnectionUseCase pluggyClientConnectionUseCase, PluggyClientItemUseCase pluggyClientItemUseCase, PluggyMapper pluggyMapper, SincronizarContaGateway sincronizarContaGateway, SincronizarTransacoesUseCase sincronizarTransacoesUseCase) {
        this.pluggyClientConnectionUseCase = pluggyClientConnectionUseCase;
        this.pluggyClientItemUseCase = pluggyClientItemUseCase;
        this.pluggyMapper = pluggyMapper;
        this.sincronizarContaGateway = sincronizarContaGateway;
        this.sincronizarTransacoesUseCase = sincronizarTransacoesUseCase;
    }

    @GetMapping
    public ResponseEntity< AcessTokenResponse> getAcessToken(@AuthenticationPrincipal UsuarioEntity usuario) {
        String acessToken = pluggyClientConnectionUseCase.execute();
        return ResponseEntity.ok(new AcessTokenResponse(acessToken));
    }

    @PostMapping("/item")
    public ResponseEntity getPluggyItem(@AuthenticationPrincipal UsuarioEntity usuario,@RequestBody CreateItemDto createItemDto){

        var domain = pluggyMapper.toDomain(createItemDto);
        domain.setUsuarioId(usuario.getId());
        pluggyClientItemUseCase.execute(domain);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/transacoes/sync")
    public void sync() {
        sincronizarTransacoesUseCase.execute();
    }








}
