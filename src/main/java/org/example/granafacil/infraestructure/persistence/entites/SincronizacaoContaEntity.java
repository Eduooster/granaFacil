package org.example.granafacil.infraestructure.persistence.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.granafacil.core.domain.enums.StatusSincronizacao;

import java.time.LocalDateTime;

@Entity
@Table(name = "GF_SINCRONIZACAO_CONTA")

@ToString
public class SincronizacaoContaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idConta;
    private String origem;

    private LocalDateTime ultimaDataSync;
    private Long ultimoIdProcessado;

    @Enumerated(EnumType.STRING)
    private StatusSincronizacao statusAtual;
    private String mensagemErro;

    private LocalDateTime dataInicioExecucao;
    private LocalDateTime dataFimExecucao;

    private Integer paginaAtual;


    private LocalDateTime atualizadoEm;

    @Override
    public String toString() {
        return "SincronizacaoContaEntity{" +
                "id=" + id +
                ", idConta=" + idConta +
                ", origem='" + origem + '\'' +
                ", ultimaDataSync=" + ultimaDataSync +
                ", ultimoIdProcessado=" + ultimoIdProcessado +
                ", statusAtual=" + statusAtual +
                ", mensagemErro='" + mensagemErro + '\'' +
                ", dataInicioExecucao=" + dataInicioExecucao +
                ", dataFimExecucao=" + dataFimExecucao +
                ", paginaAtual=" + paginaAtual +

                ", atualizadoEm=" + atualizadoEm +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdConta() {
        return idConta;
    }

    public void setIdConta(String idConta) {
        this.idConta = idConta;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public LocalDateTime getUltimaDataSync() {
        return ultimaDataSync;
    }

    public void setUltimaDataSync(LocalDateTime ultimaDataSync) {
        this.ultimaDataSync = ultimaDataSync;
    }

    public Long getUltimoIdProcessado() {
        return ultimoIdProcessado;
    }

    public void setUltimoIdProcessado(Long ultimoIdProcessado) {
        this.ultimoIdProcessado = ultimoIdProcessado;
    }

    public StatusSincronizacao getStatusAtual() {
        return statusAtual;
    }

    public void setStatusAtual(StatusSincronizacao statusAtual) {
        this.statusAtual = statusAtual;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    public void setMensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }

    public LocalDateTime getDataInicioExecucao() {
        return dataInicioExecucao;
    }

    public void setDataInicioExecucao(LocalDateTime dataInicioExecucao) {
        this.dataInicioExecucao = dataInicioExecucao;
    }

    public LocalDateTime getDataFimExecucao() {
        return dataFimExecucao;
    }

    public void setDataFimExecucao(LocalDateTime dataFimExecucao) {
        this.dataFimExecucao = dataFimExecucao;
    }

    public Integer getPaginaAtual() {
        return paginaAtual;
    }

    public void setPaginaAtual(Integer paginaAtual) {
        this.paginaAtual = paginaAtual;
    }



    public LocalDateTime getAtualizadoEm() {
        return atualizadoEm;
    }

    public void setAtualizadoEm(LocalDateTime atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
    }
}
