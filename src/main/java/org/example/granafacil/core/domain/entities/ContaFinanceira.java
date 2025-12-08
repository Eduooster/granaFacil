package org.example.granafacil.core.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ContaFinanceira{
    private Long id;
    private ConexaoOpenFinance conexaoOpenFinance;
    private String type;
    private String subtype;
    private String name;
    private BigDecimal balance;
    private String itemId;
    private String number;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private SincronizacaoConta status;

    public ContaFinanceira(Long id, ConexaoOpenFinance conexaoOpenFinance, String type, String subtype, String name, BigDecimal balance, String itemId, String number, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.conexaoOpenFinance = conexaoOpenFinance;
        this.type = type;
        this.subtype = subtype;
        this.name = name;
        this.balance = balance;
        this.itemId = itemId;
        this.number = number;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "ContaFinanceira{" +
                "id=" + id +
                ", conexaoOpenFinance=" + conexaoOpenFinance.getId() +
                ", type='" + type + '\'' +
                ", subtype='" + subtype + '\'' +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", accountId='" + itemId + '\'' +
                ", number='" + number + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }



    public static ContaFinanceira novo(ConexaoOpenFinance conexaoOpenFinance, String type, String subtype, String name, BigDecimal balance, String accountId, String number, LocalDateTime createdAt, LocalDateTime updatedAt){
        return new ContaFinanceira(null,conexaoOpenFinance,type,subtype,name,balance,accountId,number,createdAt,updatedAt);

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ConexaoOpenFinance getConexaoOpenFinance() {
        return conexaoOpenFinance;
    }

    public void setConexaoOpenFinance(ConexaoOpenFinance conexaoOpenFinance) {
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

