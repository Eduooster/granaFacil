package org.example.granafacil.core.domain.entities;

import org.example.granafacil.core.domain.enums.StatusSincronizacao;

import java.time.LocalDateTime;

public class SincronizacaoConta {



    private Long id;

    private String idConta;
    private String origem;

    private LocalDateTime ultimaDataSync;
    private Long ultimoIdProcessado;

    private StatusSincronizacao statusAtual;
    private String mensagemErro;

    private LocalDateTime dataInicioExecucao;
    private LocalDateTime dataFimExecucao;

    private Integer paginaAtual;
    private String cursor;

    private LocalDateTime atualizadoEm;

    @Override
    public String toString() {
        return "SincronizacaoConta{" +
                "id=" + id +
                ", idConta='" + idConta + '\'' +
                ", origem='" + origem + '\'' +
                ", ultimaDataSync=" + ultimaDataSync +
                ", ultimoIdProcessado=" + ultimoIdProcessado +
                ", statusAtual=" + statusAtual +
                ", mensagemErro='" + mensagemErro + '\'' +
                ", dataInicioExecucao=" + dataInicioExecucao +
                ", dataFimExecucao=" + dataFimExecucao +
                ", paginaAtual=" + paginaAtual +
                ", cursor='" + cursor + '\'' +
                ", atualizadoEm=" + atualizadoEm +
                '}';
    }

    public static SincronizacaoConta nova(String idConta) {
        SincronizacaoConta s = new SincronizacaoConta();
        s.idConta = idConta;
        s.statusAtual = StatusSincronizacao.PENDENTE;
        s.dataInicioExecucao = null;
        s.dataFimExecucao = null;
        s.ultimaDataSync = null;
        s.mensagemErro = null;
        return s;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdConta(String idConta) {
        this.idConta = idConta;
    }

    public String getIdConta() {
        return idConta;
    }



    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public LocalDateTime getUltimaDataSync() {
        return ultimaDataSync;
    }

    public void setUltimaDataSync(LocalDateTime ultimaDataSync) {
        this.ultimaDataSync = ultimaDataSync;
    }

    public Long getUltimoIdProcessado() {
        return ultimoIdProcessado;
    }

    public void setUltimoIdProcessado(Long ultimoIdProcessado) {
        this.ultimoIdProcessado = ultimoIdProcessado;
    }

    public StatusSincronizacao getStatusAtual() {
        return statusAtual;
    }

    public void setStatusAtual(StatusSincronizacao statusAtual) {
        this.statusAtual = statusAtual;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    public void setMensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }

    public LocalDateTime getDataInicioExecucao() {
        return dataInicioExecucao;
    }

    public void setDataInicioExecucao(LocalDateTime dataInicioExecucao) {
        this.dataInicioExecucao = dataInicioExecucao;
    }

    public LocalDateTime getDataFimExecucao() {
        return dataFimExecucao;
    }

    public void setDataFimExecucao(LocalDateTime dataFimExecucao) {
        this.dataFimExecucao = dataFimExecucao;
    }

    public Integer getPaginaAtual() {
        return paginaAtual;
    }

    public void setPaginaAtual(Integer paginaAtual) {
        this.paginaAtual = paginaAtual;
    }

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }

    public LocalDateTime getAtualizadoEm() {
        return atualizadoEm;
    }

    public void setAtualizadoEm(LocalDateTime atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
    }
}

