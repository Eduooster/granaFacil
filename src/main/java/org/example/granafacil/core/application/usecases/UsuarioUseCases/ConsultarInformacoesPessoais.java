package org.example.granafacil.core.application.usecases.UsuarioUseCases;

import lombok.extern.slf4j.Slf4j;
import org.example.granafacil.core.application.dtos.UsuarioDadosPessoais;
import org.example.granafacil.core.application.gateways.UsuarioRepository;
import org.example.granafacil.core.domain.entities.Usuario;

@Slf4j
public class ConsultarInformacoesPessoais {

    private final UsuarioRepository usuarioRepository;

    public ConsultarInformacoesPessoais(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioDadosPessoais execute(Long idUsuario) {

        Usuario usuario = usuarioRepository.getMe(idUsuario);

        log.info(usuario.toString());

        return new UsuarioDadosPessoais(
                usuario.getNome(),
                usuario.getSobrenome(),
                usuario.getEmail(),
                usuario.getObjetivo(),
                usuario.getFinancas(),
                usuario.getPerfil()
        );
    }
}
