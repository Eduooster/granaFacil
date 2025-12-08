package org.example.granafacil.infraestructure.persistence.entites;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.example.granafacil.core.domain.enums.FormaGerenciarFinancas;
import org.example.granafacil.core.domain.enums.ObjetivoFinanceiro;
import org.example.granafacil.core.domain.enums.PerfilFinanceiro;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Table(name = "GF_USUARIO")
@Entity
@Getter
@Setter

@RequiredArgsConstructor
public class UsuarioEntity implements UserDetails {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String nome;
    private String sobrenome;

    private String email;
    private String senhaHash;

    @Enumerated(EnumType.STRING)
    @Nullable
    private ObjetivoFinanceiro objetivo;
    @Nullable
    @Enumerated(EnumType.STRING)
    private FormaGerenciarFinancas financas;
    @Nullable
    @Enumerated(EnumType.STRING)
    private PerfilFinanceiro perfil;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<ConexaoOpenFinanceEntity> conexoes;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return senhaHash;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    @Nullable
    public ObjetivoFinanceiro getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(@Nullable ObjetivoFinanceiro objetivo) {
        this.objetivo = objetivo;
    }

    @Nullable
    public FormaGerenciarFinancas getFinancas() {
        return financas;
    }

    public void setFinancas(@Nullable FormaGerenciarFinancas financas) {
        this.financas = financas;
    }

    @Nullable
    public PerfilFinanceiro getPerfil() {
        return perfil;
    }

    public void setPerfil(@Nullable PerfilFinanceiro perfil) {
        this.perfil = perfil;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        UsuarioEntity that = (UsuarioEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return "UsuarioEntity{" +
                "id=" + id +
                ", nome='" + nome + '\'' +

                '}';
    }
}
