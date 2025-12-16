package org.example.granafacil.core.application.gateways;

import org.example.granafacil.core.domain.entities.SincronizacaoConta;
import org.example.granafacil.core.domain.enums.StatusSincronizacao;

import java.util.List;

public interface SincronizarContaRepository {

    SincronizacaoConta findByIdConta(String id);
    void save(SincronizacaoConta conta);

    List<SincronizacaoConta> buscarContasParaSincronizar(StatusSincronizacao atual);

}
