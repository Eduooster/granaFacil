package org.example.granafacil.core.application.gateways;

import java.time.Instant;

public interface TokenServiceGateway {
    String gerarToken(Long idUsuario);
    String getSubject(String token);
    Instant dataExpiracao();
}
