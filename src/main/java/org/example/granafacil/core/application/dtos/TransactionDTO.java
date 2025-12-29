package org.example.granafacil.core.application.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionDTO {

    private String id;
    private String description;
    private String descriptionRaw;
    private String currencyCode;
    private BigDecimal amount;
    private LocalDateTime date;
    private BigDecimal balance;
    private String category;
    private String categoryId;
    private String accountId;
    private String providerCode;
    private String type;      // CREDIT ou DEBIT
    private String status;
    private String pluggyId;// POSTED ou PENDING

    // getters e setters


    @Override
    public String toString() {
        return "TransactionDTO{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", descriptionRaw='" + descriptionRaw + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", balance=" + balance +
                ", category='" + category + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", accountId='" + accountId + '\'' +
                ", providerCode='" + providerCode + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", pluggyId='" + pluggyId + '\'' +
                '}';
    }

    public String getPluggyId() {
        return pluggyId;
    }

    public void setPluggyId(String pluggyId) {
        this.pluggyId = pluggyId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionRaw() {
        return descriptionRaw;
    }

    public void setDescriptionRaw(String descriptionRaw) {
        this.descriptionRaw = descriptionRaw;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}