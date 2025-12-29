package org.example.granafacil.infraestructure.persistence.repositories;

import org.example.granafacil.core.domain.enums.StatusSincronizacao;
import org.example.granafacil.core.domain.enums.TipoSincronizacao;
import org.example.granafacil.infraestructure.persistence.entites.SincronizacaoContaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SincronizacaoContaRepositoryImpl extends JpaRepository<SincronizacaoContaEntity, Long> {
    List<SincronizacaoContaEntity> findConnectorIdsByStatus(StatusSincronizacao statusSincronizacao);

    @Query("SELECT s FROM SincronizacaoContaEntity s " +
            "WHERE s.status = :status " +
            "AND s.conexao.usuario.id = :usuarioId")
    List<SincronizacaoContaEntity> findConnectorIdsByStatusAndUsuarioId(@Param("status") StatusSincronizacao status,
                                                                        @Param("usuarioId") Long usuarioId);

    @Query("""
    SELECT sc
    FROM SincronizacaoContaEntity sc
    WHERE sc.status = :status
      AND sc.tipo = :tipo
""")
    List<SincronizacaoContaEntity> buscarPorStatusETipo(
            @Param("status") StatusSincronizacao status,
            @Param("tipo") TipoSincronizacao tipo
    );

    SincronizacaoContaEntity findByContaIdAndTipo(Long contaId, TipoSincronizacao tipoSincronizacao);
}

