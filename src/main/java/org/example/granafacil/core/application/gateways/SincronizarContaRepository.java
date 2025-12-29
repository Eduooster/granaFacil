package org.example.granafacil.core.application.gateways;

import org.example.granafacil.core.domain.entities.SincronizacaoConta;
import org.example.granafacil.core.domain.enums.StatusSincronizacao;
import org.example.granafacil.core.domain.enums.TipoSincronizacao;

import java.util.List;
import java.util.Optional;

public interface SincronizarContaRepository {

    SincronizacaoConta findByIdConta(String id);
    SincronizacaoConta save(SincronizacaoConta conta);

    List<SincronizacaoConta> buscarContasParaSincronizar(StatusSincronizacao atual,TipoSincronizacao tipo);



    List<SincronizacaoConta> buscarContasParaSincronizarPorConexao(StatusSincronizacao statusSincronizacao, TipoSincronizacao tipoSincronizacao);

    SincronizacaoConta buscarPorContaETipo(Long contaId, TipoSincronizacao tipoSincronizacao);
}
