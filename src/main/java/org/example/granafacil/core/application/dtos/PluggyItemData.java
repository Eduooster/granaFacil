package org.example.granafacil.core.application.dtos;

import java.time.LocalDateTime;

public class PluggyItemData {


    private Long connectorId;
    private String connectorName;
    private String connectorImageUrl;
    private String connectorHealth;
    private String connectorTypeBank;


    private String pluggyItemId;
    private String status;
    private LocalDateTime dataCriacao;
    private LocalDateTime ultimoSync;
    private Long usuarioId;
    private Boolean ativo;

    public PluggyItemData(
            Long connectorId,
            String connectorName,
            String connectorImageUrl,
            String connectorHealth,
            String connectorTypeBank,
            String pluggyItemId,
            String status,
            LocalDateTime dataCriacao,

            LocalDateTime ultimoSync,
            Long usuarioId,
            Boolean ativo
    ) {
        this.connectorId = connectorId;
        this.connectorName = connectorName;
        this.connectorImageUrl = connectorImageUrl;
        this.connectorHealth = connectorHealth;
        this.connectorTypeBank = connectorTypeBank;
        this.pluggyItemId = pluggyItemId;
        this.status = status;
        this.dataCriacao = dataCriacao;

        this.ultimoSync = ultimoSync;
        this.usuarioId = usuarioId;
        this.ativo = ativo;
    }



    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public void setConnectorName(String connectorName) {
        this.connectorName = connectorName;
    }

    public void setConnectorImageUrl(String connectorImageUrl) {
        this.connectorImageUrl = connectorImageUrl;
    }

    public void setConnectorHealth(String connectorHealth) {
        this.connectorHealth = connectorHealth;
    }

    public void setConnectorTypeBank(String connectorTypeBank) {
        this.connectorTypeBank = connectorTypeBank;
    }

    public void setPluggyItemId(String pluggyItemId) {
        this.pluggyItemId = pluggyItemId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }



    public void setUltimoSync(LocalDateTime ultimoSync) {
        this.ultimoSync = ultimoSync;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getConnectorId() {
        return connectorId;
    }

    public void setConnectorId(Long connectorId) {
        this.connectorId = connectorId;
    }

    public String getConnectorName() {
        return connectorName;
    }

    public String getConnectorImageUrl() {
        return connectorImageUrl;
    }

    public String getConnectorHealth() {
        return connectorHealth;
    }

    public String getConnectorTypeBank() {
        return connectorTypeBank;
    }

    public String getPluggyItemId() {
        return pluggyItemId;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }



    public LocalDateTime getUltimoSync() {
        return ultimoSync;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    @Override
    public String toString() {
        return "PluggyItemData{" +
                "connectorId=" + connectorId +
                ", connectorName='" + connectorName + '\'' +
                ", connectorImageUrl='" + connectorImageUrl + '\'' +
                ", connectorHealth='" + connectorHealth + '\'' +
                ", connectorTypeBank='" + connectorTypeBank + '\'' +
                ", pluggyItemId='" + pluggyItemId + '\'' +
                ", status='" + status + '\'' +
                ", dataCriacao=" + dataCriacao +

                ", ultimoSync=" + ultimoSync +
                ", usuarioId=" + usuarioId +
                '}';
    }
}

