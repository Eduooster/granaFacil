package org.example.granafacil.infraestructure.persistence.entites;

import jakarta.persistence.*;

@Entity
@Table(name = "GF_TRANSACAO")
public class TransacaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
