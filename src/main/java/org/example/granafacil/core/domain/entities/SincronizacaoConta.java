package org.example.granafacil.core.domain.entities;

import org.example.granafacil.core.domain.enums.StatusSincronizacao;
import org.example.granafacil.core.domain.enums.TipoSincronizacao;

import java.time.Instant;
import java.time.LocalDateTime;

public class SincronizacaoConta {

    private Long id;

    private ConexaoOpenFinance conexao;
    private ContaFinanceira conta;

    private TipoSincronizacao tipo;
    private StatusSincronizacao status;

    private String erro;

    private Instant startedAt;
    private Instant finishedAt;

    private Long lastProcessedExternalId;
    private Integer currentPage;
    private String cursor;
    private Instant ultimaDataSync;

    private Instant createdAt;
    private Instant updatedAt;


    public static SincronizacaoConta iniciar(
            ConexaoOpenFinance conexao,
            ContaFinanceira conta,
            TipoSincronizacao tipo
    ) {
        SincronizacaoConta sync = new SincronizacaoConta();
        sync.conexao = conexao;
        sync.conta = conta; // pode ser null se for geral
        sync.tipo = tipo;
        sync.status = StatusSincronizacao.PENDENTE;
        sync.startedAt = Instant.now();
        return sync;
    }

    public void finalizarComSucesso() {
        this.status = StatusSincronizacao.CONCLUIDO;
        this.finishedAt = Instant.now();
    }

    public void finalizarComErro(String erro) {
        this.status = StatusSincronizacao.ERRO;
        this.erro = erro;
        this.finishedAt = Instant.now();
    }

    @Override
    public String toString() {
        return "SincronizacaoConta{" +
                "id=" + id +
                ", conexao=" + conexao.getId() +
                ", conta=" + conta +
                ", tipo=" + tipo +
                ", status=" + status +
                ", erro='" + erro + '\'' +
                ", startedAt=" + startedAt +
                ", finishedAt=" + finishedAt +
                ", lastProcessedExternalId=" + lastProcessedExternalId +
                ", currentPage=" + currentPage +
                ", cursor='" + cursor + '\'' +
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

    public ConexaoOpenFinance getConexao() {
        return conexao;
    }

    public void setConexao(ConexaoOpenFinance conexao) {
        this.conexao = conexao;
    }

    public ContaFinanceira getConta() {
        return conta;
    }

    public void setConta(ContaFinanceira conta) {
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

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
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

