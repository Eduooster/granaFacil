package org.example.granafacil.core.application.dtos.TransacaoDtos;

import org.example.granafacil.core.domain.enums.CategoriaInterna;
import org.example.granafacil.core.domain.enums.TipoMovimentacao;

import java.math.BigDecimal;
import java.time.Instant;

public record MovimentacoesDto (
        Long id,
        String descricao,
        BigDecimal valor,
        TipoMovimentacao tipoMovimentacao,
        Instant data,
        CategoriaInterna categoriaInterna


){
}
