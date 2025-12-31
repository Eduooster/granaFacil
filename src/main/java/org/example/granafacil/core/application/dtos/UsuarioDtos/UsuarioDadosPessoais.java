package org.example.granafacil.core.application.dtos.UsuarioDtos;

import org.example.granafacil.core.domain.enums.FormaGerenciarFinancas;
import org.example.granafacil.core.domain.enums.ObjetivoFinanceiro;
import org.example.granafacil.core.domain.enums.PerfilFinanceiro;


public class UsuarioDadosPessoais {

    private String nome;
    private String sobrenome;
    private String email;


    private ObjetivoFinanceiro objetivo;
    private FormaGerenciarFinancas financas;
    private PerfilFinanceiro perfil;



    public UsuarioDadosPessoais() {
    }

    public UsuarioDadosPessoais(String nome, String sobrenome, String email, ObjetivoFinanceiro objetivo, FormaGerenciarFinancas financas, PerfilFinanceiro perfil) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;

        this.objetivo = objetivo;
        this.financas = financas;
        this.perfil = perfil;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public ObjetivoFinanceiro getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(ObjetivoFinanceiro objetivo) {
        this.objetivo = objetivo;
    }

    public FormaGerenciarFinancas getFinancas() {
        return financas;
    }

    public void setFinancas(FormaGerenciarFinancas financas) {
        this.financas = financas;
    }

    public PerfilFinanceiro getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilFinanceiro perfil) {
        this.perfil = perfil;
    }


}
