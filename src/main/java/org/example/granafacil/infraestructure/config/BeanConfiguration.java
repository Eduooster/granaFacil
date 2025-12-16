package org.example.granafacil.infraestructure.config;

import org.example.granafacil.core.application.gateways.*;
import org.example.granafacil.core.application.services.OpenFinanceApplicationService;
import org.example.granafacil.core.application.usecases.OpenFinanceUseCases.*;
import org.example.granafacil.core.application.usecases.UsuarioUseCases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public RefreshTokenUseCase refreshTokenUseCase(RefreshTokenRepository refreshTokenRepository, TokenServiceGateway tokenServiceGateway, RefreshTokenGenerator refreshTokenGenerator) {
        return new RefreshTokenUseCase(refreshTokenRepository, tokenServiceGateway,refreshTokenGenerator);

    }

    @Bean
    public AutenticacaoUsuarioUseCase autenticacaoUsuarioUseCase(UsuarioRepository usuarioRepository,
                                                                 PasswordEncoderService passwordEncoderService, TokenServiceGateway tokenServiceGateway, RefreshTokenRepository refreshTokenRepository, RefreshTokenGenerator refreshTokenGenerator ) {
        return new AutenticacaoUsuarioUseCase(usuarioRepository, passwordEncoderService, tokenServiceGateway, refreshTokenRepository,refreshTokenGenerator);
    }

    @Bean
    public RegistrarUsuarioUseCase registrarUsuarioUseCase(UsuarioRepository usuarioRepository, PasswordEncoderService passwordEncoderService, TokenServiceGateway tokenServiceGateway, RefreshTokenRepository refreshTokenRepository, RefreshTokenGenerator refreshTokenGenerator) {
        return new RegistrarUsuarioUseCase(passwordEncoderService, usuarioRepository,tokenServiceGateway, refreshTokenRepository,refreshTokenGenerator);

    }

    @Bean
    public AtualizarObjetivoFinanceirolUsuario atualizarObjetivoFinanceiroUsuario(UsuarioRepository usuarioRepository) {
        return new AtualizarObjetivoFinanceirolUsuario(usuarioRepository);

    }
    @Bean
    public AtualizarFormaGerenciamentoFinancasUsuario atualizarFormaGerenciamentoFinancasUsuario(UsuarioRepository usuarioRepository) {
        return new AtualizarFormaGerenciamentoFinancasUsuario(usuarioRepository);
    }

    @Bean
    public AtualizarPerfilFinanceiroUsuario atualizarPerfilFinanceiroUsuario(UsuarioRepository usuarioRepository) {
        return new AtualizarPerfilFinanceiroUsuario(usuarioRepository);
    }

    @Bean
    public PluggyAuthUseCase pluggyAuthUseCase(PluggyGateway pluggyGateway) {
        return new PluggyAuthUseCase(pluggyGateway);
    }

    @Bean
    public PluggyClientConnectionUseCase pluggyClientConnectionUseCase(PluggyGateway pluggyGateway, PluggyAuthUseCase pluggyAuthUseCase) {
        return new PluggyClientConnectionUseCase(pluggyGateway,pluggyAuthUseCase);
    }

    @Bean
    public PluggyClientItemUseCase pluggyClientItemUseCase(UsuarioRepository usuarioRepository, ConexaoOpenFinanceRepository conexaoOpenFinanceRepository, ContaFinanceiraRepository contaFinanceiraRepository, PluggyGateway pluggyGateway , InstituicaoFinanceiraRepository instituicaoFinanceiraGateway, OpenFinanceApplicationService openFinanceApplicationService, SincronizarContaRepository sincronizarContaRepository) {
        return new PluggyClientItemUseCase(usuarioRepository, conexaoOpenFinanceRepository, contaFinanceiraRepository,pluggyGateway,instituicaoFinanceiraGateway,openFinanceApplicationService, sincronizarContaRepository);
    }

    @Bean
    public ExecutarSincronizacaoUseCase executarSincronizacaoUseCase(PluggyGateway pluggyGateway, SincronizarContaRepository sincronizarContaRepository, TransacaoRepository transacaoRepository){
        return new ExecutarSincronizacaoUseCase(pluggyGateway, sincronizarContaRepository, transacaoRepository);
    }

    @Bean
    public SincronizarTransacoesUseCase sincronizarTransacoesUseCase(SincronizarContaRepository sincronizarContaRepository, PluggyGateway pluggyGateway, ExecutarSincronizacaoUseCase executarSincronizacaoUseCase) {
        return new SincronizarTransacoesUseCase(sincronizarContaRepository,pluggyGateway,executarSincronizacaoUseCase);

    }
    @Bean
    public PegarDadosUsuario pegarDadosUsuario(UsuarioRepository usuarioRepository) {
        return new PegarDadosUsuario(usuarioRepository);
    }
}
