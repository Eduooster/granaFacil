package org.example.granafacil.core.application.gateways;

public interface PasswordEncoderGateway {
    String hash(String senhaPura);
    boolean matches(String senhaPura, String senhaHash);

}
