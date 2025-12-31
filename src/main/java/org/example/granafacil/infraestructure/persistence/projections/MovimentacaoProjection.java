package org.example.granafacil.infraestructure.persistence.projections;

import java.math.BigDecimal;
import java.time.Instant;

public interface MovimentacaoProjection {
    Long getId();
    String getDescricao();
    BigDecimal getValor();
    String getTipoMovimentacao();
    Instant getData();
    String getCategoriaInterna();
}
