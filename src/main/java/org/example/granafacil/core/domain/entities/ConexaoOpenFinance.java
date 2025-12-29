package org.example.granafacil.core.domain.entities;



import org.example.granafacil.core.domain.enums.ProviderAccount;
import org.example.granafacil.core.domain.enums.StatusConexaoOpenFinance;
import org.example.granafacil.infraestructure.persistence.entites.ContaFinanceiraEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

//representa o item
public class ConexaoOpenFinance{

    private Long id;

    private String status;

    private ProviderAccount provider;
    private String pluggyItemId;
    private Instant createdAt;
    private Instant tokenExpiresAt;


    private Instant lastSuccessfulSyncAt;

    private InstituicaoFinanceira instituicaoFinanceira;
    private Usuario usuario;
    private boolean ativo = true;
    private List<ContaFinanceiraEntity> contas;


    public ConexaoOpenFinance() {}

    public ConexaoOpenFinance(Long id, String status, ProviderAccount provider, String pluggyItemId, Instant createdAt, Instant tokenExpiresAt, Instant lastSuccessfulSyncAt, InstituicaoFinanceira instituicaoFinanceira, Usuario usuario, boolean ativo, List<ContaFinanceiraEntity> contas) {
        this.id = id;
        this.status = status;
        this.provider = provider;
        this.pluggyItemId = pluggyItemId;
        this.createdAt = createdAt;
        this.tokenExpiresAt = tokenExpiresAt;
        this.lastSuccessfulSyncAt = lastSuccessfulSyncAt;
        this.instituicaoFinanceira = instituicaoFinanceira;
        this.usuario = usuario;
        this.ativo = ativo;
        this.contas = contas;
    }

    public static ConexaoOpenFinance novo(String status, ProviderAccount provider, String externalConnectionId, Instant createdAt, Instant tokenExpiresAt, Instant lastSuccessfulSyncAt, InstituicaoFinanceira instituicaoFinanceira, Usuario usuario) {
        return new ConexaoOpenFinance(null,status,provider, externalConnectionId,createdAt,tokenExpiresAt,lastSuccessfulSyncAt,instituicaoFinanceira,usuario,true,null);

    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public List<ContaFinanceiraEntity> getContas() {
        return contas;
    }

    public void setContas(List<ContaFinanceiraEntity> contas) {
        this.contas = contas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public InstituicaoFinanceira getInstituicaoFinanceira() {
        return instituicaoFinanceira;
    }

    public void setInstituicaoFinanceira(InstituicaoFinanceira instituicaoFinanceira) {
        this.instituicaoFinanceira = instituicaoFinanceira;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "ConexaoOpenFinance{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", provider=" + provider +
                ", pluggyItemId='" + pluggyItemId + '\'' +
                ", createdAt=" + createdAt +
                ", tokenExpiresAt=" + tokenExpiresAt +
                ", lastSuccessfulSyncAt=" + lastSuccessfulSyncAt +
                ", instituicaoFinanceira=" + instituicaoFinanceira.getName() +
                ", usuario=" + usuario.getId() +
                ", ativo=" + ativo +
                '}';
    }
}
