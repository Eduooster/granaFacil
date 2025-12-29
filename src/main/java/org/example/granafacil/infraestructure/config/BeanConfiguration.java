package org.example.granafacil.infraestructure.config;

import org.example.granafacil.core.application.gateways.*;
import org.example.granafacil.core.application.orchestrator.OpenFinanceOrchestrator;
import org.example.granafacil.core.application.services.CategoriaService;
import org.example.granafacil.core.application.services.ClassificarMovimentacaoService;
import org.example.granafacil.core.application.usecases.OpenFinanceUseCases.*;
import org.example.granafacil.core.application.usecases.UsuarioUseCases.*;
import org.example.granafacil.core.application.usecases.contaFinanceiraUseCases.ConsultarContasFinanceiras;
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
    public RegistrarUsuarioUseCase registrarUsuarioUseCase(UsuarioRepository usuarioRepository, PasswordEncoderService passwordEncoderService, TokenServiceGateway tokenServiceGateway, RefreshTokenRepository refreshTokenRepository, RefreshTokenGenerator refreshTokenGenerator,ContaFinanceiraRepository contaFinanceiraRepository) {
        return new RegistrarUsuarioUseCase(passwordEncoderService, usuarioRepository,tokenServiceGateway, refreshTokenRepository,refreshTokenGenerator,contaFinanceiraRepository);

    }

    @Bean
    public AtualizarObjetivoFinanceirol atualizarObjetivoFinanceiroUsuario(UsuarioRepository usuarioRepository) {
        return new AtualizarObjetivoFinanceirol(usuarioRepository);

    }
    @Bean
    public AtualizarFormaGerenciamentoFinancas atualizarFormaGerenciamentoFinancasUsuario(UsuarioRepository usuarioRepository) {
        return new AtualizarFormaGerenciamentoFinancas(usuarioRepository);
    }

    @Bean
    public AtualizarPerfilFinanceiro atualizarPerfilFinanceiroUsuario(UsuarioRepository usuarioRepository) {
        return new AtualizarPerfilFinanceiro(usuarioRepository);
    }

    @Bean
    public GerarAutenticacaoOpenFinanceUseCase pluggyAuthUseCase(PluggyGateway pluggyGateway) {
        return new GerarAutenticacaoOpenFinanceUseCase(pluggyGateway);
    }

    @Bean
    public GerarTokenConexaoUseCase pluggyClientConnectionUseCase(PluggyGateway pluggyGateway, GerarAutenticacaoOpenFinanceUseCase gerarAutenticacaoOpenFinanceUseCase) {
        return new GerarTokenConexaoUseCase(pluggyGateway, gerarAutenticacaoOpenFinanceUseCase);
    }

    @Bean
    public CriarItemConexaoUseCase pluggyClientItemUseCase(UsuarioRepository usuarioRepository, ConexaoOpenFinanceRepository conexaoOpenFinanceRepository, InstituicaoFinanceiraRepository instituicaoFinanceiraGateway) {
        return new CriarItemConexaoUseCase(usuarioRepository, conexaoOpenFinanceRepository ,instituicaoFinanceiraGateway);
    }

    @Bean
    public ExecutarSincronizacaoTransacaoUseCase executarSincronizacaoTransacaoUseCase(PluggyGateway pluggyGateway, ContaFinanceiraRepository contaFinanceiraRepository, TransacaoRepository transacaoRepository, CategoriaService categoriaService, ClassificarMovimentacaoService classificarMovimentacaoService) {
        return new ExecutarSincronizacaoTransacaoUseCase(pluggyGateway,contaFinanceiraRepository,transacaoRepository,categoriaService,classificarMovimentacaoService);

    }

    @Bean
    public SincronizarTransacoesConexaoUseCase sincronizarTransacoesConexaoUseCase(SincronizarContaRepository sincronizarContaRepository,ConexaoOpenFinanceRepository conexaoOpenFinanceRepository,ExecutarSincronizacaoTransacaoUseCase executarSincronizacaoTransacaoUseCase,ContaFinanceiraRepository contaFinanceiraRepository) {
        return new SincronizarTransacoesConexaoUseCase(sincronizarContaRepository,conexaoOpenFinanceRepository,executarSincronizacaoTransacaoUseCase,contaFinanceiraRepository);
    }

    @Bean
    public ConsultarInformacoesPessoais pegarDadosUsuario(UsuarioRepository usuarioRepository) {
        return new ConsultarInformacoesPessoais(usuarioRepository);
    }

    @Bean
    public ConsultarContasFinanceiras consultarContasFinanceirasUsuario(ContaFinanceiraRepository contaFinanceiraRepository) {
        return new ConsultarContasFinanceiras(contaFinanceiraRepository);
    }
    @Bean
    public ImportarContasUseCase importarContasUseCase(ContaFinanceiraRepository contaFinanceiraRepository, SincronizarContaRepository sincronizarContaRepository,PluggyGateway pluggyGateway,ConexaoOpenFinanceRepository conexaoOpenFinanceRepository) {
        return new ImportarContasUseCase(contaFinanceiraRepository,sincronizarContaRepository,pluggyGateway,conexaoOpenFinanceRepository);
    }


    @Bean
    public OpenFinanceOrchestrator openFinanceOrchestrator(ImportarContasUseCase importarContasUseCase,CriarItemConexaoUseCase criarItemConexaoUseCase) {
        return new OpenFinanceOrchestrator(importarContasUseCase,criarItemConexaoUseCase);

    }
}
