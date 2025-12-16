package org.example.granafacil.core.application.usecases.UsuarioUseCases;

import org.example.granafacil.core.application.gateways.*;
import org.example.granafacil.core.domain.entities.RefreshToken;
import org.example.granafacil.core.domain.entities.Usuario;
import org.example.granafacil.infraestructure.presentation.dto.AutenticacaoDto.DadosTokenJwtResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class RegistrarUsuarioUseCase{
    private static final Logger log = LoggerFactory.getLogger(RegistrarUsuarioUseCase.class);

   private final PasswordEncoderService passwordEncoderService;
   private final UsuarioRepository usuarioRepository;
   private final TokenServiceGateway tokenServiceGateway;
   private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenGenerator refreshTokenGenerator;

    public RegistrarUsuarioUseCase(PasswordEncoderService passwordEncoderService, UsuarioRepository usuarioRepository, TokenServiceGateway tokenServiceGateway, RefreshTokenRepository refreshTokenRepository, RefreshTokenGenerator refreshTokenGenerator) {
        this.passwordEncoderService = passwordEncoderService;
        this.usuarioRepository = usuarioRepository;
        this.tokenServiceGateway = tokenServiceGateway;
        this.refreshTokenRepository = refreshTokenRepository;
        this.refreshTokenGenerator = refreshTokenGenerator;
    }


    public DadosTokenJwtResponseDto execute(
            String nome, String sobrenome, String email, String senha, String confirmarSenha
    )  {

        if (usuarioRepository.buscarPorEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        String senhaHash = passwordEncoderService.hash(senha);

        Usuario novoUsuario = Usuario.novo(
                nome,
                sobrenome,
                email,
                senhaHash
        );

        Usuario usuarioSalvo = usuarioRepository.criar(novoUsuario);

        String jwt = tokenServiceGateway.gerarToken(usuarioSalvo.getId());

        RefreshToken novoRefresh = new RefreshToken();
        novoRefresh.setUserId(usuarioSalvo.getId());
        novoRefresh.setToken(refreshTokenGenerator.generate());
        novoRefresh.setExpiresAt(LocalDateTime.now().plusDays(30));
        novoRefresh.setRevogado(false);
        novoRefresh.setCreatedAt(LocalDateTime.now());

        refreshTokenRepository.save(novoRefresh);

        return new DadosTokenJwtResponseDto(jwt, novoRefresh.getToken());
    }


}
