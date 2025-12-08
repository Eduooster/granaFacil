package org.example.granafacil.infraestructure.persistence.entites;


import jakarta.persistence.*;

@Entity
@Table(name = "GF_INSTITUICAO_FINANCEIRA")
public class InstituicaoFinanceiraEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String name;
    private String imageUrl;
    private String health;
    private String typeBank;
    private Long idConnector;

    public InstituicaoFinanceiraEntity() {}

    public Long getIdConnector() {
        return idConnector;
    }

    public void setIdConnector(Long idConnector) {
        this.idConnector = idConnector;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getTypeBank() {
        return typeBank;
    }

    public void setTypeBank(String typeBank) {
        this.typeBank = typeBank;
    }
}
