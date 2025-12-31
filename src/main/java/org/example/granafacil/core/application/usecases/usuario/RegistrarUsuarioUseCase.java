package org.example.granafacil.core.application.usecases.usuario;

import org.example.granafacil.core.application.gateways.*;
import org.example.granafacil.core.domain.entities.ContaFinanceira;
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
    private final ContaFinanceiraRepository contaFinanceiraRepository;

    public RegistrarUsuarioUseCase(PasswordEncoderService passwordEncoderService, UsuarioRepository usuarioRepository, TokenServiceGateway tokenServiceGateway, RefreshTokenRepository refreshTokenRepository, RefreshTokenGenerator refreshTokenGenerator, ContaFinanceiraRepository contaFinanceiraRepository) {
        this.passwordEncoderService = passwordEncoderService;
        this.usuarioRepository = usuarioRepository;
        this.tokenServiceGateway = tokenServiceGateway;
        this.refreshTokenRepository = refreshTokenRepository;
        this.refreshTokenGenerator = refreshTokenGenerator;
        this.contaFinanceiraRepository = contaFinanceiraRepository;
    }


    public DadosTokenJwtResponseDto execute(
            String nome, String sobrenome, String email, String senha
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

        log.info(
                usuarioSalvo.toString()
        );

        associarContaCarteira(usuarioSalvo);

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

    private void associarContaCarteira(Usuario usuario) {
        ContaFinanceira contaFinanceiraContaCarteira = ContaFinanceira.contaCarteira(usuario);
        log.info("conta criada:" + contaFinanceiraContaCarteira);
        contaFinanceiraRepository.save(contaFinanceiraContaCarteira);


    }


}
