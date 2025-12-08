package org.example.granafacil.core.application.usecases.OpenFinanceUseCases;

import lombok.extern.slf4j.Slf4j;
import org.example.granafacil.core.application.gateways.PluggyGateway;
import org.example.granafacil.core.application.gateways.SincronizarContaGateway;
import org.example.granafacil.core.domain.entities.SincronizacaoConta;
import org.example.granafacil.core.domain.enums.StatusSincronizacao;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
public class SincronizarTransacoesUseCase {

    private SincronizarContaGateway syncgateway;
    private PluggyGateway pluggyGateway;
    private ExecutarSincronizacaoUseCase executarSincronizacaoUseCase;

    public SincronizarTransacoesUseCase(SincronizarContaGateway gateway, PluggyGateway pluggygateway, ExecutarSincronizacaoUseCase executarSincronizacaoUseCase) {
        this.syncgateway = gateway;
        this.pluggyGateway = pluggygateway;
        this.executarSincronizacaoUseCase = executarSincronizacaoUseCase;
    }

    public void execute() {
        log.info("Entrando no use case");
        List<SincronizacaoConta> listaDeSync = syncgateway.buscarContasParaSincronizar(StatusSincronizacao.PENDENTE);
        log.info("Contas encontrados" + listaDeSync.size());

        for (SincronizacaoConta conta : listaDeSync) {

            if (conta.getStatusAtual() == StatusSincronizacao.EM_ANDAMENTO) {
                continue;
            }

            log.info("Verificando conta" + conta.getIdConta());

            conta.setStatusAtual(StatusSincronizacao.EM_ANDAMENTO);
            conta.setDataInicioExecucao(LocalDateTime.now());
            syncgateway.save(conta);

            boolean sucesso = true;

            try {

                executarSincronizacaoUseCase.executar(conta);
            } catch (Exception e) {
                sucesso = false;
                conta.setStatusAtual(StatusSincronizacao.ERRO);
                conta.setMensagemErro(e.getMessage());
            }

            conta.setDataFimExecucao(LocalDateTime.now());

            if (sucesso) {
                conta.setStatusAtual(StatusSincronizacao.CONCLUIDO);
                conta.setUltimaDataSync(LocalDateTime.now());
            }

            syncgateway.save(conta);
        }
    }
}
