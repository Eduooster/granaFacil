package org.example.granafacil.core.application.usecases.UsuarioUseCases;

import org.example.granafacil.core.application.gateways.*;
import org.example.granafacil.core.domain.entities.RefreshToken;
import org.example.granafacil.core.domain.entities.Usuario;

import org.example.granafacil.core.domain.exceptions.SenhaInvalidaExeception;
import org.example.granafacil.core.domain.exceptions.UsuarioNaoEncontradoException;
import org.example.granafacil.infraestructure.presentation.dto.AutenticacaoDto.DadosTokenJwtResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;
import java.time.LocalDateTime;


public class AutenticacaoUsuarioUseCase  {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoderService passwordGateway;
    private final TokenServiceGateway tokenServiceGateway;
    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenGenerator refreshTokenGenerator;
    private final SecureRandom secureRandom = new SecureRandom();
    private static final Logger log = LoggerFactory.getLogger(AutenticacaoUsuarioUseCase.class);

    public AutenticacaoUsuarioUseCase(
            UsuarioRepository usuarioRepository,
            PasswordEncoderService passwordGateway,
            TokenServiceGateway tokenServiceGateway, RefreshTokenRepository refreshTokenRepository, RefreshTokenGenerator refreshTokenGenerator) {
        this.usuarioRepository = usuarioRepository;
        this.passwordGateway = passwordGateway;
        this.tokenServiceGateway = tokenServiceGateway;

        this.refreshTokenRepository = refreshTokenRepository;
        this.refreshTokenGenerator = refreshTokenGenerator;
    }

    public DadosTokenJwtResponseDto execute(String email, String senha) {

        Usuario usuario = usuarioRepository.buscarPorEmail(email)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario nao encontrado"));



        boolean senhaValida = passwordGateway.matches(senha, usuario.getSenhaHash());
        if (!senhaValida) {
            throw new SenhaInvalidaExeception("Senha invalida");
        }

        String token = tokenServiceGateway.gerarToken(usuario.getId());



        RefreshToken novoRefresh = new RefreshToken();
        novoRefresh.setUserId(usuario.getId());
        novoRefresh.setToken(refreshTokenGenerator.generate());
        novoRefresh.setExpiresAt(LocalDateTime.now().plusDays(30));
        novoRefresh.setRevogado(false);
        novoRefresh.setCreatedAt(LocalDateTime.now());

        refreshTokenRepository.save(novoRefresh);




        return new DadosTokenJwtResponseDto(token, novoRefresh.getToken()) ;

    }



}
