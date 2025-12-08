package org.example.granafacil.core.domain.entities;

import org.example.granafacil.core.domain.enums.FormaGerenciarFinancas;
import org.example.granafacil.core.domain.enums.ObjetivoFinanceiro;
import org.example.granafacil.core.domain.enums.PerfilFinanceiro;

public class Usuario {

    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
    private String senhaHash;

    private ObjetivoFinanceiro objetivo;
    private FormaGerenciarFinancas financas;
    private PerfilFinanceiro perfil;

    // Construtores
    public Usuario() {
    }

    public Usuario(Long id, String nome, String sobrenome, String email, String senhaHash,
                   ObjetivoFinanceiro objetivo, FormaGerenciarFinancas financas, PerfilFinanceiro perfil) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.senhaHash = senhaHash;

        this.objetivo = objetivo;
        this.financas = financas;
        this.perfil = perfil;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getSobrenome() { return sobrenome; }
    public void setSobrenome(String sobrenome) { this.sobrenome = sobrenome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenhaHash() { return senhaHash; }
    public void setSenhaHash(String senhaHash) { this.senhaHash = senhaHash; }



    public ObjetivoFinanceiro getObjetivo() { return objetivo; }
    public void setObjetivo(ObjetivoFinanceiro objetivo) { this.objetivo = objetivo; }

    public FormaGerenciarFinancas getFinancas() { return financas; }
    public void setFinancas(FormaGerenciarFinancas financas) { this.financas = financas; }

    public PerfilFinanceiro getPerfil() { return perfil; }
    public void setPerfil(PerfilFinanceiro perfil) { this.perfil = perfil; }


    public static Usuario novo(String nome, String sobrenome, String email, String senhaHash) {
        return new Usuario(null, nome, sobrenome, email, senhaHash, null, null, null);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", email='" + email + '\'' +
                ", senhaHash='" + senhaHash + '\'' +
                + '\'' +
                ", objetivo=" + objetivo +
                ", financas=" + financas +
                ", perfil=" + perfil +
                '}';
    }

}
