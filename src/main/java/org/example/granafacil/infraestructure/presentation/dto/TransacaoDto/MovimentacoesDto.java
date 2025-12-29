package org.example.granafacil.infraestructure.presentation.dto.TransacaoDto;

import org.example.granafacil.core.domain.enums.TipoMovimentacao;

import java.math.BigDecimal;
import java.time.Instant;

public class MovimentacoesDto {

    private Long id;
    private String descricao;
    private BigDecimal valor;
    private TipoMovimentacao tipoMovimentacao;
    private Instant data;


}
