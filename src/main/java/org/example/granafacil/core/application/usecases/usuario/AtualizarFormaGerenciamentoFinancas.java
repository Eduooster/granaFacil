package org.example.granafacil.core.application.usecases.usuario;

import org.example.granafacil.core.application.gateways.UsuarioRepository;
import org.example.granafacil.core.domain.entities.Usuario;
import org.example.granafacil.core.domain.enums.FormaGerenciarFinancas;

public class AtualizarFormaGerenciamentoFinancas {

    private final UsuarioRepository usuarioRepository;

    public AtualizarFormaGerenciamentoFinancas(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void execute(Usuario usuario, FormaGerenciarFinancas formaGerenciarFinancas) {
        Usuario usuarioBanco = usuarioRepository.buscarPorEmail(usuario.getEmail()).orElse(null);
        usuarioBanco.setFinancas(formaGerenciarFinancas);
        usuarioRepository.save(usuarioBanco);
    }
}
