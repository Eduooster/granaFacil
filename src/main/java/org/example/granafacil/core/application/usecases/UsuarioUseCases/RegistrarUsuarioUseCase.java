package org.example.granafacil.core.application.usecases.UsuarioUseCases;

import org.example.granafacil.core.application.gateways.PasswordEncoderGateway;
import org.example.granafacil.core.application.gateways.TokenServiceGateway;
import org.example.granafacil.core.application.gateways.UsuarioGateway;
import org.example.granafacil.core.domain.entities.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegistrarUsuarioUseCase{
    private static final Logger log = LoggerFactory.getLogger(RegistrarUsuarioUseCase.class);
   private final PasswordEncoderGateway passwordEncoderGateway;
   private final UsuarioGateway usuarioGateway;
   private final TokenServiceGateway tokenServiceGateway;

    public RegistrarUsuarioUseCase(PasswordEncoderGateway passwordEncoderGateway, UsuarioGateway usuarioGateway, TokenServiceGateway tokenServiceGateway) {
        this.passwordEncoderGateway = passwordEncoderGateway;
        this.usuarioGateway = usuarioGateway;
        this.tokenServiceGateway = tokenServiceGateway;
    }


    public String execute(String nome, String sobrenome, String email, String senha,
                           String confirmarSenha) {

        log.info("Executando registro de usuario");

        Usuario usuario = usuarioGateway.buscarPorEmail(email)
                .orElse(null);

        if (usuario != null) {
            throw new IllegalArgumentException("Email já cadastrado");
        }



        String senhaHash = passwordEncoderGateway.hash(senha);




        Usuario novoUsuario = Usuario.novo(
                nome,
                sobrenome,
                email,
                senhaHash
        );


        Usuario usuarioSalvo = usuarioGateway.criar(novoUsuario);

        return tokenServiceGateway.gerarToken(usuarioSalvo.getId());
    }
}
