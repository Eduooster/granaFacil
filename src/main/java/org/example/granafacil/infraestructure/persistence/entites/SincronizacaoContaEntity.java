package org.example.granafacil.infraestructure.persistence.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.granafacil.core.domain.enums.StatusSincronizacao;
import org.example.granafacil.core.domain.enums.TipoSincronizacao;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "GF_SINCRONIZACAO_CONTA")


public class SincronizacaoContaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "conexao_id", nullable = false)
    private ConexaoOpenFinanceEntity conexao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_id")
    private ContaFinanceiraEntity conta;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoSincronizacao tipo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusSincronizacao status;

    private String erro;

    private Instant startedAt;
    private Instant finishedAt;

    private Instant ultimaDataSync;

    private Long lastProcessedExternalId;
    private Integer currentPage;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    @Override
    public String toString() {
        return "SincronizacaoContaEntity{" +
                "id=" + id +
                ", conexao=" +
                ", conta=" + conta +
                ", tipo=" + tipo +
                ", status=" + status +
                ", erro='" + erro + '\'' +
                ", startedAt=" + startedAt +
                ", finishedAt=" + finishedAt +
                ", lastProcessedExternalId=" + lastProcessedExternalId +
                ", currentPage=" + currentPage +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public Instant getUltimaDataSync() {
        return ultimaDataSync;
    }

    public void setUltimaDataSync(Instant ultimaDataSync) {
        this.ultimaDataSync = ultimaDataSync;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ConexaoOpenFinanceEntity getConexao() {
        return conexao;
    }

    public void setConexao(ConexaoOpenFinanceEntity conexao) {
        this.conexao = conexao;
    }

    public ContaFinanceiraEntity getConta() {
        return conta;
    }

    public void setConta(ContaFinanceiraEntity conta) {
        this.conta = conta;
    }

    public TipoSincronizacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoSincronizacao tipo) {
        this.tipo = tipo;
    }

    public StatusSincronizacao getStatus() {
        return status;
    }

    public void setStatus(StatusSincronizacao status) {
        this.status = status;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public Instant getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Instant startedAt) {
        this.startedAt = startedAt;
    }

    public Instant getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(Instant finishedAt) {
        this.finishedAt = finishedAt;
    }

    public Long getLastProcessedExternalId() {
        return lastProcessedExternalId;
    }

    public void setLastProcessedExternalId(Long lastProcessedExternalId) {
        this.lastProcessedExternalId = lastProcessedExternalId;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
