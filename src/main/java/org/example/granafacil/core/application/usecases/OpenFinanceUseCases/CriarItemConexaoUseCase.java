package org.example.granafacil.core.application.usecases.OpenFinanceUseCases;

import org.example.granafacil.core.application.dtos.PluggyItemAccounts;
import org.example.granafacil.core.application.dtos.PluggyItemData;
import org.example.granafacil.core.application.gateways.*;
import org.example.granafacil.core.domain.entities.*;
import org.example.granafacil.core.domain.enums.ProviderAccount;
import org.example.granafacil.core.domain.exceptions.ConexaoOpenFinanceDuplicada;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Optional;

public class CriarItemConexaoUseCase {

    private static final Logger log = LoggerFactory.getLogger(CriarItemConexaoUseCase.class);

    private final UsuarioRepository usuarioRepository;
    private final ConexaoOpenFinanceRepository conexaoGateway;
    private final InstituicaoFinanceiraRepository instituicaoGateway;



    public CriarItemConexaoUseCase(UsuarioRepository usuarioRepository, ConexaoOpenFinanceRepository conexaoGateway, InstituicaoFinanceiraRepository instituicaoGateway) {
        this.usuarioRepository = usuarioRepository;
        this.conexaoGateway = conexaoGateway;

        this.instituicaoGateway = instituicaoGateway;

    }


    public ConexaoOpenFinance execute(PluggyItemData data) {
        log.trace("Executando criar Item" + data);

        Optional<ConexaoOpenFinance> connRepo = conexaoGateway.findByPluggyItemIdAndAtivoTrue(data.getPluggyItemId());

        if (connRepo.isPresent()) {
            throw  new ConexaoOpenFinanceDuplicada("Conexao id duplicada");
        }


        Usuario usuario = usuarioRepository.buscaIdUsuario(data.getUsuarioId());

        InstituicaoFinanceira inst = resolverInstituicao(data);

        ConexaoOpenFinance conexaoOpenFinance =  resolverConexao(data, usuario, inst);

        return conexaoOpenFinance;

    }



    private InstituicaoFinanceira resolverInstituicao(PluggyItemData data) {

        return instituicaoGateway.buscarPorIdConnector(data.getConnectorId())
                .orElseGet(() -> {
                    var nova = InstituicaoFinanceira.novo(
                            data.getConnectorId(),
                            data.getConnectorName(),
                            data.getConnectorImageUrl(),
                            data.getConnectorHealth(),
                            data.getConnectorTypeBank()
                    );

                    return instituicaoGateway.salvar(nova);
                });
    }

    private ConexaoOpenFinance resolverConexao(PluggyItemData data, Usuario usuario, InstituicaoFinanceira inst) {

        boolean conexaoJaExiste = conexaoGateway.existsByPluggyItemId(data.getPluggyItemId());

        if (conexaoJaExiste) {
            throw new ConexaoOpenFinanceDuplicada("Conexao duplicada");
        }

        var ultimoSync = Instant.now();
        Instant expiracao = Instant.now()
                .atZone(ZoneId.systemDefault())
                .plusMonths(3)
                .toInstant();



        ConexaoOpenFinance conn = ConexaoOpenFinance.novo(
                data.getStatus(),
                ProviderAccount.EXTERNAL,
                data.getPluggyItemId(),
                Instant.now(),
                expiracao,
                ultimoSync,
                inst,
                usuario
        );



        return  conexaoGateway.salvar(conn);
    }



}
