package org.example.granafacil.infraestructure.persistence.projections;

import java.math.BigDecimal;

public interface FinanceiroResumoProjection {

    BigDecimal getReceita();

    BigDecimal getDespesa();

    BigDecimal getVariacao();
}
