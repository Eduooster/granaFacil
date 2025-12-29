package org.example.granafacil.infraestructure.persistence.entites;

import jakarta.persistence.*;
import org.example.granafacil.core.domain.enums.CategoriaInterna;
import org.example.granafacil.core.domain.enums.OrigemTransacao;
import org.example.granafacil.core.domain.enums.TipoMovimentacao;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "GF_TRANSACAO")
public class TransacaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;


    @Column(name = "external_transaction_id", nullable = false, updatable = false)
    private String externalTransactionId;


    private Long contaId;

    private String descricao;
    private String descricaoRaw;
    private String categoria;
    private String categoriaId;
    private String tipo;
    private String status;
    private String moeda;
    private BigDecimal valor;
    private Instant data;

    @Enumerated(EnumType.STRING)
    private OrigemTransacao origemTransacao;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    @Enumerated(EnumType.STRING)
    private TipoMovimentacao tipoMovimentacao;
    @Enumerated(EnumType.STRING)
    private CategoriaInterna categoriaInterna;

    private Boolean ativo = Boolean.TRUE;

    @Override
    public String toString() {
        return "TransacaoEntity{" +
                "id=" + id +

                ", contaId='"  + '\'' +
                ", descricao='" + descricao + '\'' +
                ", descricaoRaw='" + descricaoRaw + '\'' +
                ", categoria='" + categoria + '\'' +
                ", categoriaId='" + categoriaId + '\'' +
                ", tipo='" + tipo + '\'' +
                ", status='" + status + '\'' +
                ", moeda='" + moeda + '\'' +
                ", valor=" + valor +
                ", data=" + data +

                ", ativo=" + ativo +
                '}';
    }

    public TipoMovimentacao getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public CategoriaInterna getCategoriaInterna() {
        return categoriaInterna;
    }

    public void setCategoriaInterna(CategoriaInterna categoriaInterna) {
        this.categoriaInterna = categoriaInterna;
    }



    public Long getContaId() {
        return contaId;
    }

    public void setContaId(Long contaId) {
        this.contaId = contaId;
    }

    public String getExternalTransactionId() {
        return externalTransactionId;
    }

    public void setExternalTransactionId(String externalTransactionId) {
        this.externalTransactionId = externalTransactionId;
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

    public Instant getData() {
        return data;
    }

    public void setData(Instant data) {
        this.data = data;
    }



    public OrigemTransacao getOrigemTransacao() {
        return origemTransacao;
    }

    public void setOrigemTransacao(OrigemTransacao origemTransacao) {
        this.origemTransacao = origemTransacao;
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

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
