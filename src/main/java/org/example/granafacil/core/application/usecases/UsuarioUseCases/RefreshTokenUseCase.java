package org.example.granafacil.core.application.usecases.UsuarioUseCases;

import lombok.extern.slf4j.Slf4j;
import org.example.granafacil.core.application.gateways.RefreshTokenRepository;
import org.example.granafacil.core.application.gateways.RefreshTokenGenerator;
import org.example.granafacil.core.application.gateways.TokenServiceGateway;
import org.example.granafacil.core.domain.entities.RefreshToken;
import org.example.granafacil.core.domain.exceptions.UnauthorizedException;
import org.example.granafacil.infraestructure.presentation.dto.AutenticacaoDto.DadosTokenJwtResponseDto;
import org.example.granafacil.infraestructure.presentation.dto.AutenticacaoDto.RefreshTokenRequest;

import java.time.LocalDateTime;

@Slf4j
public class RefreshTokenUseCase {

    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenServiceGateway tokenServiceGateway;
    private final RefreshTokenGenerator refreshTokenGenerator;

    public RefreshTokenUseCase(RefreshTokenRepository refreshTokenRepository, TokenServiceGateway tokenServiceGateway, RefreshTokenGenerator refreshTokenGenerator) {

        this.refreshTokenRepository = refreshTokenRepository;
        this.tokenServiceGateway = tokenServiceGateway;
        this.refreshTokenGenerator = refreshTokenGenerator;
    }

    public DadosTokenJwtResponseDto execute(String jwtExpirado, RefreshTokenRequest refreshTokenRequest)
             {


        RefreshToken refreshToken = refreshTokenRepository.findByToken(refreshTokenRequest.refreshToken());
        if (refreshToken == null) {
            throw new UnauthorizedException("Refresh token não encontrado");
        }


        if (refreshToken.isRevogado()) {
            throw new UnauthorizedException("Refresh token revogado");
        }

        if (refreshToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new UnauthorizedException("Refresh token expirado");
        }


        String subject = tokenServiceGateway.getSubject(jwtExpirado);
        Long userIdJwt = Long.parseLong(subject);


        if (!refreshToken.getUserId().equals(userIdJwt)) {
            throw new UnauthorizedException("Refresh token não pertence ao usuário");
        }


        log.info("Refresh token: " + refreshToken);
        log.info("refreshRequest: " + refreshTokenRequest);


        RefreshToken novoRefresh = new RefreshToken();
        novoRefresh.setUserId(userIdJwt);
        novoRefresh.setToken(refreshTokenGenerator.generate());
        novoRefresh.setExpiresAt(LocalDateTime.now().plusDays(30));
        novoRefresh.setRevogado(false);
        novoRefresh.setCreatedAt(LocalDateTime.now());

        refreshTokenRepository.save(novoRefresh);


        String novoJwt = tokenServiceGateway.gerarToken(userIdJwt);

        refreshTokenRepository.deletarRefreshUsuarioId(refreshToken.getUserId(), refreshToken.getToken());

        return new DadosTokenJwtResponseDto(novoJwt, novoRefresh.getToken());
    }




}
