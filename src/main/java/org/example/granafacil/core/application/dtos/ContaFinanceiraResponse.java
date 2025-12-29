package org.example.granafacil.core.application.dtos;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ContaFinanceiraResponse {
    private Long id;
    private String name;
    private Long balanceInCents;
    private String imageUrl;
    private String institutionName;

    public ContaFinanceiraResponse(
            Long id,
            String name,
            BigDecimal balance,
            String imageUrl,
            String institutionName
    ) {
        this.id = id;
        this.name = name;
        this.balanceInCents = balance
                .setScale(2, RoundingMode.HALF_EVEN)
                .movePointRight(2)
                .longValueExact();
        this.imageUrl = imageUrl;
        this.institutionName = (institutionName != null) ? institutionName : "Carteira";
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBalanceInCents() {
        return balanceInCents;
    }

    public void setBalanceInCents(Long balanceInCents) {
        this.balanceInCents = balanceInCents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBalance() {
        return balanceInCents;
    }

    public void setBalance(Long balance) {
        this.balanceInCents = balance;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }
}
