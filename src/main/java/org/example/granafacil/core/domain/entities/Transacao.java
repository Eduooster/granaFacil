package org.example.granafacil.core.domain.entities;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.example.granafacil.core.domain.enums.CategoriaInterna;

import org.example.granafacil.core.domain.enums.OrigemTransacao;
import org.example.granafacil.core.domain.enums.TipoMovimentacao;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

public class Transacao {

    private Long id;

    private Long  contaId;
    private String externalTransactionId;

    private String descricao;
    private String descricaoRaw;
    private String categoria;
    private String categoriaId;
    private String tipo;
    private String status;
    private String moeda;
    private BigDecimal valor;
    private Instant data;


    private TipoMovimentacao tipoMovimentacao;


    private CategoriaInterna categoriaInterna;

    private Instant dataImportacao;
    private Instant dataAtualizacao;

    private Boolean ativo;

    private OrigemTransacao origemTransacao;

    public TipoMovimentacao getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public String getExternalTransactionId() {
        return externalTransactionId;
    }

    public void setExternalTransactionId(String externalTransactionId) {
        this.externalTransactionId = externalTransactionId;
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "id=" + id +

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

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }





    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Long getContaId() {
        return contaId;
    }

    public void setContaId(Long contaId) {
        this.contaId = contaId;
    }

    public Instant getData() {
        return data;
    }

    public void setData(Instant data) {
        this.data = data;
    }

    public Instant getDataImportacao() {
        return dataImportacao;
    }

    public void setDataImportacao(Instant dataImportacao) {
        this.dataImportacao = dataImportacao;
    }

    public Instant getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Instant dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public OrigemTransacao getOrigemTransacao() {
        return origemTransacao;
    }

    public void setOrigemTransacao(OrigemTransacao origemTransacao) {
        this.origemTransacao = origemTransacao;
    }
}


