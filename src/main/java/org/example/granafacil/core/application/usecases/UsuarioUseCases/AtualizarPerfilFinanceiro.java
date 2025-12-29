package org.example.granafacil.core.application.usecases.UsuarioUseCases;

import org.example.granafacil.core.application.gateways.UsuarioRepository;
import org.example.granafacil.core.domain.entities.Usuario;
import org.example.granafacil.core.domain.enums.PerfilFinanceiro;

public class AtualizarPerfilFinanceiro {

    private final UsuarioRepository usuarioRepository;

    public AtualizarPerfilFinanceiro(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void execute(Usuario usuario, PerfilFinanceiro perfil) {

        Usuario usuarioBanco = usuarioRepository.buscarPorEmail(usuario.getEmail()).orElse(null);
        usuarioBanco.setPerfil(perfil);
        usuarioRepository.save(usuarioBanco);

    }
}
