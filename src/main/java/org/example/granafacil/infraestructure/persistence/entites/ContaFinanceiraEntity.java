package org.example.granafacil.infraestructure.persistence.entites;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity

@Table(name = "GF_CONTA_FINANCEIRA")
public class ContaFinanceiraEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conexao_id")
    private ConexaoOpenFinanceEntity conexaoOpenFinance;




    private String type;
    private String subtype;
    private String name;
    private BigDecimal balance;
    private String itemId;
    @Column(name = "ACCOUNT_NUMBER")
    private String number;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Override
    public String toString() {
        return "ContaFinanceiraEntity{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", subtype='" + subtype + '\'' +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", itemId='" + itemId + '\'' +
                ", number='" + number + '\'' +

                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ConexaoOpenFinanceEntity getConexaoOpenFinance() {
        return conexaoOpenFinance;
    }

    public void setConexaoOpenFinance(ConexaoOpenFinanceEntity conexaoOpenFinance) {
        this.conexaoOpenFinance = conexaoOpenFinance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }



    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
