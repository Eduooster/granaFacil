package org.example.granafacil.infraestructure.persistence.adapters;

import jakarta.transaction.Transactional;
import org.example.granafacil.core.application.gateways.RefreshTokenRepository;
import org.example.granafacil.core.domain.entities.RefreshToken;
import org.example.granafacil.infraestructure.persistence.mapper.RefreshMapper;
import org.example.granafacil.infraestructure.persistence.repositories.RefreshTokenRepositoryImpl;
import org.springframework.stereotype.Component;

@Component
public class JpaRefreshTokenAdapter implements RefreshTokenRepository {

    private final RefreshTokenRepositoryImpl refreshTokenRepositoryImpl;
    private final RefreshMapper refreshMapper;


    public JpaRefreshTokenAdapter(RefreshTokenRepositoryImpl refreshTokenRepositoryImpl, RefreshMapper refreshMapper) {
        this.refreshTokenRepositoryImpl = refreshTokenRepositoryImpl;
        this.refreshMapper = refreshMapper;

    }

    @Override
    public RefreshToken findByToken(String refreshToken) {

        var entity = refreshTokenRepositoryImpl.findByToken(refreshToken);
        if (entity == null) return null;
        return refreshMapper.toDomain(entity);
    }

    @Override
    public void save(RefreshToken refreshToken) {
        refreshTokenRepositoryImpl.save(refreshMapper.toEntity(refreshToken));
    }

    @Override
    public void excluir(RefreshToken refreshToken) {
        refreshTokenRepositoryImpl.delete(refreshMapper.toEntity(refreshToken));
    }

    public RefreshToken buscarPorUsuarioId(Long usuarioId) {
        var entity = refreshTokenRepositoryImpl.findByUserId(usuarioId);
        if (entity == null) return null;
        return refreshMapper.toDomain(entity);
    }



    @Override
    @Transactional
    public void invalidarRefreshsUsuario(Long id) {
        refreshTokenRepositoryImpl.marcarComoRevogado(id);
    }

    @Transactional
    @Override
    public void deletarRefreshUsuarioId(Long idUsuario,String refreshToken) {
        refreshTokenRepositoryImpl.deletarRefreshTokenPorUsuario(idUsuario,refreshToken);
    }
}
