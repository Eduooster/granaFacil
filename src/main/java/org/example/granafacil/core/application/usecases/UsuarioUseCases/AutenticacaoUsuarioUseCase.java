package org.example.granafacil.core.application.usecases.UsuarioUseCases;

import org.example.granafacil.core.application.gateways.PasswordEncoderGateway;
import org.example.granafacil.core.application.gateways.TokenServiceGateway;
import org.example.granafacil.core.application.gateways.UsuarioGateway;
import org.example.granafacil.core.domain.entities.Usuario;

import org.example.granafacil.core.domain.exceptions.SenhaInvalidaExeception;
import org.example.granafacil.core.domain.exceptions.UsuarioNaoEncontradoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AutenticacaoUsuarioUseCase  {

    private final UsuarioGateway usuarioGateway;
    private final PasswordEncoderGateway passwordGateway;
    private final TokenServiceGateway tokenServiceGateway;

    private static final Logger log = LoggerFactory.getLogger(AutenticacaoUsuarioUseCase.class);

    public AutenticacaoUsuarioUseCase(
            UsuarioGateway usuarioGateway,
            PasswordEncoderGateway passwordGateway,
            TokenServiceGateway tokenServiceGateway) {
        this.usuarioGateway = usuarioGateway;
        this.passwordGateway = passwordGateway;
        this.tokenServiceGateway = tokenServiceGateway;

    }

    public String execute(String email, String senha) {

        Usuario usuario = usuarioGateway.buscarPorEmail(email)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario nao encontrado"));

        boolean senhaValida = passwordGateway.matches(senha, usuario.getSenhaHash());
        if (!senhaValida) {
            throw new SenhaInvalidaExeception("Senha invalida");
        }

        return  tokenServiceGateway.gerarToken(usuario.getId());

    }

}
