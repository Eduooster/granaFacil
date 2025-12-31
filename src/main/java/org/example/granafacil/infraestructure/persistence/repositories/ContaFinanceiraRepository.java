package org.example.granafacil.infraestructure.persistence.repositories;

import org.example.granafacil.core.application.dtos.ContaFinanceiraDtos.ContaFinanceiraResponse;
import org.example.granafacil.core.domain.entities.ContaFinanceira;
import org.example.granafacil.infraestructure.persistence.entites.ContaFinanceiraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ContaFinanceiraRepository extends JpaRepository<ContaFinanceiraEntity, Long> {

            @Query("""

            SELECT new org.example.granafacil.core.application.dtos.ContaFinanceiraDtos.ContaFinanceiraResponse(
                                                                    c.id,
            c.name,
            c.currentBalance,
            inst.imageUrl,
            inst.name
        )
        FROM ContaFinanceiraEntity c
        LEFT JOIN c.instituicaoFinanceira inst
        WHERE c.usuario.id = :usuarioId
        """)
    List<ContaFinanceiraResponse> findContasByUsuarioIdResponse(@Param("usuarioId") Long usuarioId);

    List<ContaFinanceira> findContasByUsuarioId(Long usuarioId);

    @Query("""
    SELECT c
    FROM ContaFinanceiraEntity c
    WHERE c.externalAccountId = :externalAccountId
      
""")
    Optional<ContaFinanceiraEntity> findByExternalAccountId(
            @Param("externalAccountId") String externalAccountId
    );

    @Query("""
    SELECT c
    FROM ContaFinanceiraEntity c
    WHERE c.conexao.id = :conexaoId
      
""")
    List<ContaFinanceiraEntity> findByConexaoOpenFinanceId(
            @Param("conexaoId") Long conexaoId
    );

    boolean existsByUsuarioIdAndInstituicaoFinanceiraIdConnectorAndNumber(Long usuarioId, Long c, String number);
}
