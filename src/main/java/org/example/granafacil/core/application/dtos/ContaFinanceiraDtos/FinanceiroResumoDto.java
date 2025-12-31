package org.example.granafacil.core.application.dtos.ContaFinanceiraDtos;

import java.math.BigDecimal;

public record FinanceiroResumoDto(BigDecimal receita, BigDecimal despesa,BigDecimal variacao) {
}
