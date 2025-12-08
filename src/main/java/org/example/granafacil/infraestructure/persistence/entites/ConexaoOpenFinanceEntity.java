package org.example.granafacil.infraestructure.persistence.entites;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "GF_CONEXAO_OPEN_FINANCE")



public class ConexaoOpenFinanceEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @OneToMany(mappedBy = "conexaoOpenFinance", cascade = CascadeType.ALL)
    private List<ContaFinanceiraEntity> contas;
    private String status;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataExpiracaoToken;
    private LocalDateTime ultimoSync;
    private String pluggyItemId;
    @ManyToOne
    private InstituicaoFinanceiraEntity instituicaoFinanceira;
    private boolean ativo = true;

    @ManyToOne(fetch = FetchType.LAZY)
    private UsuarioEntity usuario;

    @Override
    public String toString() {
        return "ConexaoOpenFinanceEntity{" +
                "usuario=" + usuario +
                ", instituicaoFinanceira=" + instituicaoFinanceira +
                ", pluggyItemId='" + pluggyItemId + '\'' +
                ", ultimoSync=" + ultimoSync +
                ", dataExpiracaoToken=" + dataExpiracaoToken +
                ", dataCriacao=" + dataCriacao +
                ", status='" + status + '\'' +
                ", contas=" + contas +
                ", id=" + id +
                '}';
    }

    public boolean getAtivo() {
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

    public List<ContaFinanceiraEntity> getContas() {
        return contas;
    }

    public void setContas(List<ContaFinanceiraEntity> contas) {
        this.contas = contas;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataExpiracaoToken() {
        return dataExpiracaoToken;
    }

    public void setDataExpiracaoToken(LocalDateTime dataExpiracaoToken) {
        this.dataExpiracaoToken = dataExpiracaoToken;
    }

    public LocalDateTime getUltimoSync() {
        return ultimoSync;
    }

    public void setUltimoSync(LocalDateTime ultimoSync) {
        this.ultimoSync = ultimoSync;
    }

    public String getPluggyItemId() {
        return pluggyItemId;
    }

    public void setPluggyItemId(String pluggyItemId) {
        this.pluggyItemId = pluggyItemId;
    }

    public InstituicaoFinanceiraEntity getInstituicaoFinanceira() {
        return instituicaoFinanceira;
    }

    public void setInstituicaoFinanceira(InstituicaoFinanceiraEntity instituicaoFinanceira) {
        this.instituicaoFinanceira = instituicaoFinanceira;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }
}
