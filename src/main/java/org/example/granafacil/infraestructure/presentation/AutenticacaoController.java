package org.example.granafacil.infraestructure.presentation;

import jakarta.validation.Valid;
import org.example.granafacil.core.application.usecases.UsuarioUseCases.AutenticacaoUsuarioUseCase;
import org.example.granafacil.core.application.usecases.UsuarioUseCases.RegistrarUsuarioUseCase;
import org.example.granafacil.infraestructure.presentation.dto.AutenticacaoDto.UsuarioLoginRequestDto;
import org.example.granafacil.infraestructure.presentation.dto.AutenticacaoDto.DadosTokenJwtResponseDto;
import org.example.granafacil.infraestructure.presentation.dto.UsuarioDto.UsuarioRegisterRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    private static final Logger log = LoggerFactory.getLogger(AutenticacaoController.class);
    private final AutenticacaoUsuarioUseCase autenticacaoUsuarioUseCase;
    private final RegistrarUsuarioUseCase registrarUsuarioUseCase;

    public AutenticacaoController(AutenticacaoUsuarioUseCase autenticacaoUsuarioUseCase, RegistrarUsuarioUseCase registrarUsuarioUseCase) {
        this.autenticacaoUsuarioUseCase = autenticacaoUsuarioUseCase;
        this.registrarUsuarioUseCase = registrarUsuarioUseCase;

    }

    @PostMapping("/login")
    public ResponseEntity autenticar(@Valid @RequestBody UsuarioLoginRequestDto dados) {
        String tokenJwt = autenticacaoUsuarioUseCase.execute(dados.email(), dados.senha());
        return ResponseEntity.ok(new DadosTokenJwtResponseDto(tokenJwt));
    }

    @PostMapping("/register")
    public ResponseEntity registrar(@Valid  @RequestBody UsuarioRegisterRequestDto dto, UriComponentsBuilder uriBuilder) {

        String jwtNovoUsuario = registrarUsuarioUseCase.execute(
                dto.nome(),dto.sobrenome(),dto.email(),dto.senha(),dto.confirmarSenha());

        return ResponseEntity.status(HttpStatus.CREATED).body(new DadosTokenJwtResponseDto(jwtNovoUsuario));
    }

}
