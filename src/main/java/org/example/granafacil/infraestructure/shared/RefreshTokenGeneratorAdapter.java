package org.example.granafacil.infraestructure.shared;

import org.example.granafacil.core.application.gateways.RefreshTokenGenerator;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;

@Component
public class RefreshTokenGeneratorAdapter implements RefreshTokenGenerator {

    private final SecureRandom secureRandom = new SecureRandom();

    @Override
    public String generate() {
        byte[] bytes = new byte[64];
        secureRandom.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
}
