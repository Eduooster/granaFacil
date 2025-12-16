package org.example.granafacil.infraestructure.presentation;

import jakarta.validation.Valid;
import org.example.granafacil.core.application.usecases.UsuarioUseCases.AutenticacaoUsuarioUseCase;
import org.example.granafacil.core.application.usecases.UsuarioUseCases.RefreshTokenUseCase;
import org.example.granafacil.core.application.usecases.UsuarioUseCases.RegistrarUsuarioUseCase;
import org.example.granafacil.core.domain.exceptions.UnauthorizedException;
import org.example.granafacil.infraestructure.presentation.dto.AutenticacaoDto.RefreshTokenRequest;
import org.example.granafacil.infraestructure.presentation.dto.AutenticacaoDto.UsuarioLoginRequestDto;
import org.example.granafacil.infraestructure.presentation.dto.AutenticacaoDto.DadosTokenJwtResponseDto;
import org.example.granafacil.infraestructure.presentation.dto.UsuarioDto.UsuarioRegisterRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    private static final Logger log = LoggerFactory.getLogger(AutenticacaoController.class);
    private final AutenticacaoUsuarioUseCase autenticacaoUsuarioUseCase;
    private final RegistrarUsuarioUseCase registrarUsuarioUseCase;
    private final RefreshTokenUseCase refreshTokenUseCase;

    public AutenticacaoController(AutenticacaoUsuarioUseCase autenticacaoUsuarioUseCase, RegistrarUsuarioUseCase registrarUsuarioUseCase, RefreshTokenUseCase refreshTokenUseCase) {
        this.autenticacaoUsuarioUseCase = autenticacaoUsuarioUseCase;
        this.registrarUsuarioUseCase = registrarUsuarioUseCase;

        this.refreshTokenUseCase = refreshTokenUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity autenticar(@Valid @RequestBody UsuarioLoginRequestDto dados) throws NoSuchAlgorithmException {
        DadosTokenJwtResponseDto dadosAuth = autenticacaoUsuarioUseCase.execute(dados.email(), dados.senha());
        return ResponseEntity.ok(dadosAuth);
    }

    @PostMapping("/register")
    public ResponseEntity registrar(@Valid  @RequestBody UsuarioRegisterRequestDto dto, UriComponentsBuilder uriBuilder) throws NoSuchAlgorithmException {

        DadosTokenJwtResponseDto dadosAuth = registrarUsuarioUseCase.execute(
                dto.nome(),dto.sobrenome(),dto.email(),dto.senha(),dto.confirmarSenha());

        return ResponseEntity.status(HttpStatus.CREATED).body(dadosAuth);
    }

    @PostMapping("/refresh")
    public ResponseEntity refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest,  @RequestHeader("Authorization") String authorization) throws NoSuchAlgorithmException {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            throw new UnauthorizedException("Authorization header inválido");
        }
        String jwt = authorization.substring(7);
        return ResponseEntity.ok(refreshTokenUseCase.execute(jwt,refreshTokenRequest));


    }

}
