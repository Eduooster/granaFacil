package org.example.granafacil.core.domain.entities;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.example.granafacil.core.domain.enums.OrigemTransacao;
import org.example.granafacil.core.domain.enums.ProviderAccount;
import org.example.granafacil.core.domain.enums.SubtipoConta;
import org.example.granafacil.core.domain.enums.TipoConta;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

public class ContaFinanceira{
    private Long id;
    private Usuario usuario;
    private InstituicaoFinanceira instituicaoFinanceira;
    private String tipo;
    private String subtipo;
    private String name;
    private BigDecimal currentBalance;
    private Instant balanceUpdatedAt;

    private String externalAccountId;
    private ProviderAccount provider;
    private String number;
    private ConexaoOpenFinance conexao;


    private Instant createdAt;
    private Instant updatedAt;

    @Override
    public String toString() {
        return "ContaFinanceira{" +
                "id=" + id +
                ", usuario=" + usuario +
                ", instituicaoFinanceira=" + instituicaoFinanceira +
                ", tipo='" + tipo + '\'' +
                ", subtipo='" + subtipo + '\'' +
                ", name='" + name + '\'' +
                ", currentBalance=" + currentBalance +
                ", balanceUpdatedAt=" + balanceUpdatedAt +
                ", externalAccountId='" + externalAccountId + '\'' +
                ", provider=" + provider +
                ", number='" + number + '\'' +
                ", conexaoOpenFinance=" + conexao +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public ContaFinanceira(Long id, Usuario usuario, InstituicaoFinanceira instituicaoFinanceira, String tipo, String subtipo, String name, BigDecimal currentBalance, Instant balanceUpdatedAt, String externalAccountId, ProviderAccount provider, String number, ConexaoOpenFinance conexao, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.usuario = usuario;
        this.instituicaoFinanceira = instituicaoFinanceira;
        this.tipo = tipo;
        this.subtipo = subtipo;
        this.name = name;
        this.conexao = conexao;
        this.currentBalance = currentBalance;
        this.balanceUpdatedAt = balanceUpdatedAt;

        this.externalAccountId = externalAccountId;
        this.provider = provider;
        this.number = number;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static ContaFinanceira novo(Usuario usuario, InstituicaoFinanceira instituicaoFinanceira, String tipo, String subtipo, String name, BigDecimal currentBalance, String externalAccountId, ProviderAccount provider, String number,ConexaoOpenFinance conexaoOpenFinance) {
        return new ContaFinanceira(null,usuario,instituicaoFinanceira,tipo,subtipo,name,currentBalance,null,externalAccountId,provider,number,conexaoOpenFinance,null,null);
    }

    public static ContaFinanceira contaCarteira(Usuario usuario) {
        return new ContaFinanceira(null,usuario,null,"Conta carteira" , "Conta Carteira",null,BigDecimal.ZERO, null,null,ProviderAccount.MANUAL,"teste",null,null,null);
    }

    public ConexaoOpenFinance getConexao() {
        return conexao;
    }

    public void setConexao(ConexaoOpenFinance conexao) {
        this.conexao = conexao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public InstituicaoFinanceira getInstituicaoFinanceira() {
        return instituicaoFinanceira;
    }

    public void setInstituicaoFinanceira(InstituicaoFinanceira instituicaoFinanceira) {
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

    public ProviderAccount getProvider() {
        return provider;
    }

    public void setProvider(ProviderAccount provider) {
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

