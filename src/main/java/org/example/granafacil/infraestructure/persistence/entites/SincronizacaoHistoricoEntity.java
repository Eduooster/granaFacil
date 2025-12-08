package org.example.granafacil.infraestructure.persistence.entites;

import jakarta.persistence.*;
import org.example.granafacil.core.domain.enums.StatusSincronizacao;

import java.time.LocalDateTime;

@Entity
@Table(name = "GF_SINCRONIZACAO_HISTORICO")
public class SincronizacaoHistoricoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idSincronizacaoConta; // FK
    private StatusSincronizacao statusFinal;

    private String mensagemErro;

    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;

    private Integer qtdTransacoesProcessadas;

    private Integer paginaUsada;
    private String cursorUsado;

    private String logDetalhado; // JSON opcional
}
