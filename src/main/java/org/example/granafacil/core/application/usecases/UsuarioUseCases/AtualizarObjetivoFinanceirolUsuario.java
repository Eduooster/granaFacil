package org.example.granafacil.core.application.usecases.UsuarioUseCases;

import org.example.granafacil.core.application.gateways.UsuarioGateway;
import org.example.granafacil.core.domain.entities.Usuario;
import org.example.granafacil.core.domain.enums.ObjetivoFinanceiro;

public class AtualizarObjetivoFinanceirolUsuario {

    private final UsuarioGateway usuarioGateway;

    public AtualizarObjetivoFinanceirolUsuario(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public void execute(Usuario usuario, ObjetivoFinanceiro objetivoFinanceiro) {
        Usuario usuarioBanco = usuarioGateway.buscarPorEmail(usuario.getEmail()).orElse(null);

        usuarioBanco.setObjetivo(objetivoFinanceiro);
        usuarioGateway.save(usuarioBanco);

    }
}
