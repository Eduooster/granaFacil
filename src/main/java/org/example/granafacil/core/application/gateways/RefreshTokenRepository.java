package org.example.granafacil.core.application.gateways;

import org.example.granafacil.core.domain.entities.RefreshToken;

public interface RefreshTokenRepository {

    RefreshToken findByToken(String refreshToken);
    void save(RefreshToken refreshToken);

    void excluir(RefreshToken refreshToken);
    RefreshToken buscarPorUsuarioId(Long usuarioId);



    void invalidarRefreshsUsuario(Long id);

    void deletarRefreshUsuarioId(Long idUsuario,String refreshToken);
}
