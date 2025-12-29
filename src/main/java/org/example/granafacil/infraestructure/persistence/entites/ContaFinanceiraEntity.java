package org.example.granafacil.infraestructure.persistence.entites;

import jakarta.persistence.*;
import org.example.granafacil.core.domain.enums.OrigemTransacao;
import org.example.granafacil.core.domain.enums.SubtipoConta;
import org.example.granafacil.core.domain.enums.TipoConta;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity

@Table(name = "GF_CONTA_FINANCEIRA")
public class ContaFinanceiraEntity {


    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @ManyToOne(optional = true)
    @JoinColumn(name = "conexao_id", nullable = true)
    private ConexaoOpenFinanceEntity conexao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instituicao_id")
    private InstituicaoFinanceiraEntity instituicaoFinanceira;


    @Column(nullable = false)
    private String tipo;


    private String subtipo;


    private String name;

    private BigDecimal currentBalance;
    private Instant balanceUpdatedAt;

    private String externalAccountId;
    private String provider;


    //adiconar flag de ativo


    @Column(name = "ACCOUNT_NUMBER")
    private String number;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    public ConexaoOpenFinanceEntity getConexao() {
        return conexao;
    }

    public void setConexao(ConexaoOpenFinanceEntity conexao) {
        this.conexao = conexao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public InstituicaoFinanceiraEntity getInstituicaoFinanceira() {
        return instituicaoFinanceira;
    }

    public void setInstituicaoFinanceira(InstituicaoFinanceiraEntity instituicaoFinanceira) {
        this.instituicaoFinanceira = instituicaoFinanceira;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSubtipo() {
        return subtipo;
    }

    public void setSubtipo(String subtipo) {
        this.subtipo = subtipo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    public Instant getBalanceUpdatedAt() {
        return balanceUpdatedAt;
    }

    public void setBalanceUpdatedAt(Instant balanceUpdatedAt) {
        this.balanceUpdatedAt = balanceUpdatedAt;
    }

    public String getExternalAccountId() {
        return externalAccountId;
    }

    public void setExternalAccountId(String externalAccountId) {
        this.externalAccountId = externalAccountId;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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
