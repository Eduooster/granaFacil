package org.example.granafacil.core.application.usecases.UsuarioUseCases;

import org.example.granafacil.core.application.gateways.UsuarioRepository;
import org.example.granafacil.core.domain.entities.Usuario;
import org.example.granafacil.core.domain.enums.ObjetivoFinanceiro;

public class AtualizarObjetivoFinanceirol {

    private final UsuarioRepository usuarioRepository;

    public AtualizarObjetivoFinanceirol(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void execute(Usuario usuario, ObjetivoFinanceiro objetivoFinanceiro) {
        Usuario usuarioBanco = usuarioRepository.buscarPorEmail(usuario.getEmail()).orElse(null);

        usuarioBanco.setObjetivo(objetivoFinanceiro);
        usuarioRepository.save(usuarioBanco);

    }
}
