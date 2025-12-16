package org.example.granafacil.core.domain.entities;


import java.time.Instant;
import java.time.LocalDateTime;

public class RefreshToken {


    private Long id;

    private Long userId;

    private String token;

    private LocalDateTime createdAt;

    private LocalDateTime expiresAt;

    private boolean revogado;

    public RefreshToken() {
    }


    public Long getId() {
        return id;
    }


    public void revogar(){
        this.revogado = true;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }



    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public boolean isRevogado() {
        return revogado;
    }

    public void setRevogado(boolean revogado) {
        this.revogado = revogado;
    }
}

