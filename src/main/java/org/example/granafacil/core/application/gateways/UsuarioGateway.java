package org.example.granafacil.core.application.gateways;

import org.example.granafacil.core.domain.entities.Usuario;
import org.example.granafacil.core.domain.enums.FormaGerenciarFinancas;
import org.example.granafacil.core.domain.enums.ObjetivoFinanceiro;
import org.example.granafacil.core.domain.enums.PerfilFinanceiro;

import java.util.Optional;

public interface UsuarioGateway {
    void save(Usuario usuario);
    Usuario criar(Usuario usuario);
    Optional<Usuario> buscarPorEmail(String email);





    Usuario buscaIdUsuario(Long id);

    void atualizarObjetivo(Usuario usuario, ObjetivoFinanceiro objetivo);

    void atualizarFormaGerenciarFinancas(Usuario usuario, FormaGerenciarFinancas forma);

    void atualizarPerfilFinanceiro(Usuario usuario, PerfilFinanceiro perfil);
}
