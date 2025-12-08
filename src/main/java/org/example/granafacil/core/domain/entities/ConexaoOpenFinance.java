package org.example.granafacil.core.domain.entities;



import java.time.LocalDateTime;

//representa o item
public class ConexaoOpenFinance{

    private Long id;
    private String status;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataExpiracaoToken;
    private LocalDateTime ultimoSync;
    private String pluggyItemId;
    private InstituicaoFinanceira instituicaoFinanceira;//connector
    private Usuario usuario;
    private boolean ativo = true;

    public ConexaoOpenFinance() {}

    public ConexaoOpenFinance(Long id, String status, LocalDateTime dataCriacao, LocalDateTime dataExpiracaoToken, LocalDateTime ultimoSync, String pluggyItemId, InstituicaoFinanceira instituicaoFinanceira, Usuario usuario, Boolean ativo) {
        this.id = id;
        this.status = status;
        this.dataCriacao = dataCriacao;
        this.dataExpiracaoToken = dataExpiracaoToken;
        this.ultimoSync = ultimoSync;
        this.pluggyItemId = pluggyItemId;
        this.instituicaoFinanceira = instituicaoFinanceira;
        this.usuario = usuario;
        this.ativo = ativo;
    }

    public static ConexaoOpenFinance novo(
            String status, LocalDateTime dataCriacao, LocalDateTime dataExpiracaoToken, LocalDateTime ultimoSync, String pluggyItemId, InstituicaoFinanceira instituicaoFinanceira, Usuario usuario, Boolean ativo

    ){
        return new ConexaoOpenFinance(null,status,dataCriacao,dataExpiracaoToken,ultimoSync,pluggyItemId,instituicaoFinanceira,usuario,ativo);
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
                ", dataCriacao=" + dataCriacao +
                ", dataExpiracaoToken=" + dataExpiracaoToken +
                ", ultimoSync=" + ultimoSync +
                ", pluggyItemId='" + pluggyItemId + '\'' +
                ", instituicaoFinanceira=" + instituicaoFinanceira +
                ", usuario=" + usuario.getId() +
                '}';
    }
}
