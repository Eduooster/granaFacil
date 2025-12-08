package org.example.granafacil.core.domain.entities;


public class InstituicaoFinanceira{
    private Long id;
    private Long idConnector;
    private String name;
    private String imageUrl;
    private String health;
    private String typeBank;

    public InstituicaoFinanceira(Long id, Long idConnector, String name, String imageUrl, String health, String typeBank) {
        this.id = id;
        this.idConnector = idConnector;
        this.name = name;
        this.imageUrl = imageUrl;
        this.health = health;
        this.typeBank = typeBank;
    }

    public static InstituicaoFinanceira novo(
            Long idConnector,
            String name,
            String imageUrl,
            String health,
            String typeBank){
        return new InstituicaoFinanceira(null,idConnector,name,imageUrl,health,typeBank);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InstituicaoFinanceira() {}

    public Long getIdConnector() {
        return idConnector;
    }

    public void setIdConnector(Long idConnector) {
        this.idConnector = idConnector;
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

    @Override
    public String toString() {
        return "InstituicaoFinanceira{" +
                "idConnector=" + idConnector +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", health='" + health + '\'' +
                ", typeBank='" + typeBank + '\'' +
                '}';
    }
}

