package org.example.granafacil.infraestructure.persistence.repositories;

import org.example.granafacil.infraestructure.persistence.projections.FinanceiroResumoProjection;
import org.example.granafacil.infraestructure.persistence.projections.MovimentacaoProjection;
import org.example.granafacil.core.domain.entities.Transacao;
import org.example.granafacil.infraestructure.persistence.entites.TransacaoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

@Repository
public interface TransacaoRepositoryImpl extends JpaRepository<TransacaoEntity, Long> {




    Optional<Transacao> findByContaIdAndExternalTransactionId(Long contaId, String externalTransactionId);


    @Query(value = """
    SELECT
        COALESCE(SUM(CASE WHEN t.TIPO_MOVIMENTACAO = 'RECEITA' THEN t.valor ELSE 0 END), 0) AS receita,
        COALESCE(SUM(CASE WHEN t.TIPO_MOVIMENTACAO = 'DESPESA' THEN t.valor ELSE 0 END), 0) AS despesa,
        COALESCE(
            SUM(CASE WHEN t.TIPO_MOVIMENTACAO = 'RECEITA' THEN t.valor ELSE 0 END), 0
        ) -
        COALESCE(
            SUM(CASE WHEN t.TIPO_MOVIMENTACAO = 'DESPESA' THEN t.valor ELSE 0 END), 0
        ) AS variacao
    FROM GF_TRANSACAO t
    JOIN GF_CONTA_FINANCEIRA c ON c.id = t.conta_Id
    WHERE c.usuario_id = :usuarioId
      AND t.ativo = 1
      AND t.data BETWEEN :inicio AND :fim
""", nativeQuery = true)
    FinanceiroResumoProjection calcularResumoFinanceiro(Long usuarioId, Instant inicio, Instant fim);


    @Query("""
    SELECT
        t.id AS id,
        t.descricao AS descricao,
        t.valor AS valor,
        t.tipoMovimentacao AS tipoMovimentacao,
        t.data AS data,
        t.categoriaInterna AS categoriaInterna
    
    FROM TransacaoEntity t
    where t.ativo = true
        and t.data BETWEEN :inicio AND :fim
        and t.contaId in(
           select c.id
           from ContaFinanceiraEntity c
           where c.usuario.id = :usuarioId
        )
    
""")
    Page<MovimentacaoProjection> buscarMovimentacoesRecentes(Long usuarioId, Instant inicio, Instant fim, Pageable pageable);
}