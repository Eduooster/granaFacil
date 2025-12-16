package org.example.granafacil.core.domain.entities;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.example.granafacil.core.domain.enums.CategoriaInterna;

import org.example.granafacil.core.domain.enums.OrigemTransacao;

import java.time.LocalDateTime;

public class Transacao {

    private Long id;
    private String pluggyId;
    private String contaId;

    private String descricao;
    private String descricaoRaw;
    private String categoria;
    private String categoriaId;
    private String tipo;
    private String status;
    private String moeda;
    private Double valor;
    private LocalDateTime data;
    private Double saldo;
    @Enumerated(EnumType.STRING)
    private CategoriaInterna categoriaInterna;

    private LocalDateTime dataImportacao;
    private LocalDateTime dataAtualizacao;

    private Boolean ativo;
    @Enumerated(EnumType.STRING)
    private OrigemTransacao origemTransacao;

    @Override
    public String toString() {
        return "Transacao{" +
                "id=" + id +
                ", pluggyId='" + pluggyId + '\'' +
                ", contaId='" + contaId + '\'' +
                ", descricao='" + descricao + '\'' +
                ", descricaoRaw='" + descricaoRaw + '\'' +
                ", categoria='" + categoria + '\'' +
                ", categoriaId='" + categoriaId + '\'' +
                ", tipo='" + tipo + '\'' +
                ", status='" + status + '\'' +
                ", moeda='" + moeda + '\'' +
                ", valor=" + valor +
                ", data=" + data +
                ", saldo=" + saldo +
                ", dataImportacao=" + dataImportacao +
                ", dataAtualizacao=" + dataAtualizacao +
                ", ativo=" + ativo +
                '}';
    }

    public CategoriaInterna getCategoriaInterna() {
        return categoriaInterna;
    }

    public void setCategoriaInterna(CategoriaInterna categoriaInterna) {
        this.categoriaInterna = categoriaInterna;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPluggyId() {
        return pluggyId;
    }

    public void setPluggyId(String pluggyId) {
        this.pluggyId = pluggyId;
    }

    public String getContaId() {
        return contaId;
    }

    public void setContaId(String contaId) {
        this.contaId = contaId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricaoRaw() {
        return descricaoRaw;
    }

    public void setDescricaoRaw(String descricaoRaw) {
        this.descricaoRaw = descricaoRaw;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(String categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMoeda() {
        return moeda;
    }

    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public LocalDateTime getDataImportacao() {
        return dataImportacao;
    }

    public void setDataImportacao(LocalDateTime dataImportacao) {
        this.dataImportacao = dataImportacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}


