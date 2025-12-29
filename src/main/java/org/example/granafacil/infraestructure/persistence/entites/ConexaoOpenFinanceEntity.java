package org.example.granafacil.infraestructure.persistence.entites;


import jakarta.persistence.*;
import org.example.granafacil.core.domain.enums.ProviderAccount;
import org.example.granafacil.core.domain.enums.StatusConexaoOpenFinance;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "GF_CONEXAO_OPEN_FINANCE")



public class ConexaoOpenFinanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private UsuarioEntity usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    private InstituicaoFinanceiraEntity instituicaoFinanceira;


    private String status;

    @Enumerated(EnumType.STRING)
    private ProviderAccount provider;
    private String pluggyItemId;

    private Instant createdAt;
    private Instant tokenExpiresAt;
    private Instant lastSuccessfulSyncAt;
    private boolean ativo = true;
    @OneToMany(mappedBy = "conexao")
    private List<ContaFinanceiraEntity> contas;

    public ConexaoOpenFinanceEntity() {
    }

    public ConexaoOpenFinanceEntity(Long id, UsuarioEntity usuario, InstituicaoFinanceiraEntity instituicaoFinanceira, String status, ProviderAccount provider, String pluggyItemId, Instant createdAt, Instant tokenExpiresAt, Instant lastSuccessfulSyncAt, boolean ativo, List<ContaFinanceiraEntity> contas) {
        this.id = id;
        this.usuario = usuario;
        this.instituicaoFinanceira = instituicaoFinanceira;
        this.status = status;
        this.provider = provider;
        this.pluggyItemId = pluggyItemId;
        this.createdAt = createdAt;
        this.tokenExpiresAt = tokenExpiresAt;
        this.lastSuccessfulSyncAt = lastSuccessfulSyncAt;
        this.ativo = ativo;
        this.contas = contas;
    }

    public List<ContaFinanceiraEntity> getContas() {
        return contas;
    }

    public void setContas(List<ContaFinanceiraEntity> contas) {
        this.contas = contas;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ProviderAccount getProvider() {
        return provider;
    }

    public void setProvider(ProviderAccount provider) {
        this.provider = provider;
    }

    public String getPluggyItemId() {
        return pluggyItemId;
    }

    public void setPluggyItemId(String pluggyItemId) {
        this.pluggyItemId = pluggyItemId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getTokenExpiresAt() {
        return tokenExpiresAt;
    }

    public void setTokenExpiresAt(Instant tokenExpiresAt) {
        this.tokenExpiresAt = tokenExpiresAt;
    }

    public Instant getLastSuccessfulSyncAt() {
        return lastSuccessfulSyncAt;
    }

    public void setLastSuccessfulSyncAt(Instant lastSuccessfulSyncAt) {
        this.lastSuccessfulSyncAt = lastSuccessfulSyncAt;
    }
}
