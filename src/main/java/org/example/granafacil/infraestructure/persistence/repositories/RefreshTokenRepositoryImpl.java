package org.example.granafacil.infraestructure.persistence.repositories;

import jakarta.transaction.Transactional;
import org.example.granafacil.infraestructure.persistence.entites.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RefreshTokenRepositoryImpl extends JpaRepository<RefreshTokenEntity, Long> {
    RefreshTokenEntity findByToken(String refreshToken);

    RefreshTokenEntity findByUserId(Long usuarioId);


    void deleteByUserId(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE RefreshTokenEntity r SET r.revogado = true WHERE r.userId = :id")
    void marcarComoRevogado(Long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM RefreshTokenEntity r WHERE r.userId = :idUsuario AND r.token =: refreshToken")
    void deletarRefreshTokenPorUsuario(Long idUsuario, String refreshToken);

    String id(Long id);
}
