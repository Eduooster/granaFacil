package org.example.granafacil.infraestructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.example.granafacil.core.application.gateways.TokenServiceGateway;
import org.example.granafacil.infraestructure.persistence.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenServiceAdapter implements TokenServiceGateway {
    @Value(value = "${api.security.token.secret}")
    private String secret;

    private final UsuarioRepository usuarioRepository;

    public TokenServiceAdapter(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public String gerarToken(Long idUsuario) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API GranaFacil")
                    .withSubject(idUsuario.toString())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
        } catch (Exception exception) {
            throw new RuntimeException("erro ao gerrar token jwt", exception);
        }
    }

    @Override
    public String getSubject(String token) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("API GranaFacil")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }

    @Override
    public Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(20).toInstant(ZoneOffset.of("-03:00"));
    }
}
