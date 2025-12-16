package org.example.granafacil.infraestructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.example.granafacil.core.application.gateways.TokenServiceGateway;
import org.example.granafacil.infraestructure.persistence.repositories.UsuarioRepositoryImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenServiceAdapter implements TokenServiceGateway {
    @Value(value = "${api.security.token.secret}")
    private String secret;

    private final UsuarioRepositoryImpl usuarioRepositoryImpl;

    public TokenServiceAdapter(UsuarioRepositoryImpl usuarioRepositoryImpl) {
        this.usuarioRepositoryImpl = usuarioRepositoryImpl;
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

            DecodedJWT decoded = JWT.decode(token);
            String subject = decoded.getSubject();

            // 2. Valida somente assinatura (não expiração)
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algoritmo)
                    .withIssuer("API GranaFacil")
                    .build();

            try {
                verifier.verify(token);
            } catch (TokenExpiredException e) {

            }

            return subject;

        } catch (JWTVerificationException e) {
            throw new RuntimeException("Token inválido!");
        }
    }

    @Override
    public Instant dataExpiracao() {

        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
