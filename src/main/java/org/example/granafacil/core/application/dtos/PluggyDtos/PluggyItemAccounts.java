package org.example.granafacil.core.application.dtos.PluggyDtos;

import java.math.BigDecimal;
import java.time.Instant;

public class PluggyItemAccounts {
    private String id;
    private String type;
    private String subtype;
    private String name;
    private BigDecimal balance;
    private String itemId;
    private String number;
    private Instant createdAt;
    private Instant updatedAt;




    @Override
    public String toString() {
        return "PluggyItemAccounts{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", subtype='" + subtype + '\'' +
                ", name='" + name + '\'' +
                ", balance=" + balance +

                ", number='" + number + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +


                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
