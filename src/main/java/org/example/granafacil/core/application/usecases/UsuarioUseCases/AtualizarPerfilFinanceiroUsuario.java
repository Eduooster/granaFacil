package org.example.granafacil.core.application.usecases.UsuarioUseCases;

import org.example.granafacil.core.application.gateways.UsuarioGateway;
import org.example.granafacil.core.domain.entities.Usuario;
import org.example.granafacil.core.domain.enums.PerfilFinanceiro;

public class AtualizarPerfilFinanceiroUsuario {

    private final UsuarioGateway usuarioGateway;

    public AtualizarPerfilFinanceiroUsuario(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public void execute(Usuario usuario, PerfilFinanceiro perfil) {

        Usuario usuarioBanco = usuarioGateway.buscarPorEmail(usuario.getEmail()).orElse(null);
        usuarioBanco.setPerfil(perfil);
        usuarioGateway.save(usuarioBanco);

    }
}
