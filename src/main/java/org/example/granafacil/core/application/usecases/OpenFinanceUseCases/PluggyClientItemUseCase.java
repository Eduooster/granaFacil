package org.example.granafacil.core.application.usecases.OpenFinanceUseCases;

import org.example.granafacil.core.application.dtos.PluggyItemAccounts;

import org.example.granafacil.core.application.dtos.PluggyItemData;
import org.example.granafacil.core.application.gateways.*;
import org.example.granafacil.core.application.services.OpenFinanceApplicationService;
import org.example.granafacil.core.domain.entities.*;
import org.example.granafacil.core.domain.enums.OrigemTransacao;
import org.example.granafacil.core.domain.exceptions.ItemJaRegistrado;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PluggyClientItemUseCase {

    private static final Logger log = LoggerFactory.getLogger(PluggyClientItemUseCase.class);

    private final UsuarioRepository usuarioRepository;
    private final ConexaoOpenFinanceRepository conexaoGateway;
    private final ContaFinanceiraRepository contaFinanceiraRepository;
    private final PluggyGateway pluggyGateway;
    private final InstituicaoFinanceiraRepository instituicaoGateway;
    private final OpenFinanceApplicationService openFinanceApplicationService;
    private final SincronizarContaRepository sincronizarContaRepository;


    public PluggyClientItemUseCase(UsuarioRepository usuarioRepository, ConexaoOpenFinanceRepository conexaoGateway, ContaFinanceiraRepository contaFinanceiraRepository, PluggyGateway pluggyGateway, InstituicaoFinanceiraRepository instituicaoGateway, OpenFinanceApplicationService openFinanceApplicationService, SincronizarContaRepository sincronizarContaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.conexaoGateway = conexaoGateway;
        this.contaFinanceiraRepository = contaFinanceiraRepository;
        this.pluggyGateway = pluggyGateway;
        this.instituicaoGateway = instituicaoGateway;
        this.openFinanceApplicationService = openFinanceApplicationService;
        this.sincronizarContaRepository = sincronizarContaRepository;
    }


    public void execute(PluggyItemData data) {

        Optional<ConexaoOpenFinance> connRepo = conexaoGateway.buscarPorItemId(data.getPluggyItemId());

        if (connRepo.isPresent()) {
            throw new ItemJaRegistrado("Item ja registrado!");
        }

        Usuario usuario = usuarioRepository.buscaIdUsuario(data.getUsuarioId());


        InstituicaoFinanceira inst = resolverInstituicao(data);

        ConexaoOpenFinance conexaoOpenFinance =  resolverConexao(data, usuario, inst);

        List<ContaFinanceira> contasFinanceira = converterPluggyItemAccountsToContaFinanceira(conexaoOpenFinance);

        sincronizarContas(conexaoOpenFinance, contasFinanceira);




    }

    private List<ContaFinanceira> converterPluggyItemAccountsToContaFinanceira(ConexaoOpenFinance conexaoOpenFinance) {
        List<PluggyItemAccounts> contas = pluggyGateway.getItemsAccount(conexaoOpenFinance.getPluggyItemId());

        return contas.stream().map(c->
                ContaFinanceira.novo(conexaoOpenFinance,c.getType(),c.getSubtype(),c.getName(),c.getBalance(),c.getId(),c.getNumber(),c.getCreatedAt(),c.getUpdatedAt(),OrigemTransacao.OPEN_FINANCE)).collect(Collectors.toList());

    }

    public void sincronizarContas(ConexaoOpenFinance conexao,List<ContaFinanceira> contas) {

        contaFinanceiraRepository.saveAll(contas);


        contas.forEach(conta -> {

            sincronizarContaRepository.save(
                    SincronizacaoConta.nova(conta.getItemId()));

        });
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
        Optional<ConexaoOpenFinance> existente = conexaoGateway.findByPluggyItemIdAndAtivoTrue(data.getPluggyItemId());


        ConexaoOpenFinance conn = openFinanceApplicationService.atualizarConexao(
                existente,
                usuario,
                inst,
                data.getPluggyItemId(),
                data.getStatus(),
                data.getDataCriacao(),
                data.getAtivo()
        );


        return  conexaoGateway.salvar(conn);
    }



}
