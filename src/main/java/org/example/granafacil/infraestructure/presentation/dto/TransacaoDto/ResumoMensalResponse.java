package org.example.granafacil.infraestructure.presentation.dto.TransacaoDto;

import org.example.granafacil.core.domain.enums.TipoMovimentacao;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class ResumoMensalResponse {

    private BigDecimal receita;
    private BigDecimal despesa;
    private BigDecimal variacao;
    private String insight;
    private List<MovimentacoesDto> movimentacoes;


}
