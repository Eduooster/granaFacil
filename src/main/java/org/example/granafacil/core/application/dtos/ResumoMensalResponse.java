package org.example.granafacil.core.application.dtos;

import org.example.granafacil.core.application.dtos.ContaFinanceiraDtos.FinanceiroResumoDto;
import org.example.granafacil.core.application.dtos.MetasDtos.MetasResumoDto;
import org.example.granafacil.core.application.dtos.TransacaoDtos.MovimentacoesDto;
import org.springframework.data.domain.Page;

import java.util.List;

public class ResumoMensalResponse {

    private FinanceiroResumoDto financeiroResumoDto;
    private InsightDto insightDto;
    private List<MovimentacoesDto> movimentacoes;
    private List<MetasResumoDto> metasResumo;

    public ResumoMensalResponse(FinanceiroResumoDto financeiroResumoDto, InsightDto insightDto, List<MovimentacoesDto> movimentacoes, List<MetasResumoDto> metasResumo) {
        this.financeiroResumoDto = financeiroResumoDto;
        this.insightDto = insightDto;
        this.movimentacoes = movimentacoes;
        this.metasResumo = metasResumo;
    }

    public FinanceiroResumoDto getFinanceiroResumoDto() {
        return financeiroResumoDto;
    }

    public void setFinanceiroResumoDto(FinanceiroResumoDto financeiroResumoDto) {
        this.financeiroResumoDto = financeiroResumoDto;
    }

    public InsightDto getInsightDto() {
        return insightDto;
    }

    public void setInsightDto(InsightDto insightDto) {
        this.insightDto = insightDto;
    }

    public List<MovimentacoesDto> getMovimentacoes() {
        return movimentacoes;
    }

    public void setMovimentacoes(List<MovimentacoesDto> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }

    public List<MetasResumoDto> getMetasResumo() {
        return metasResumo;
    }

    public void setMetasResumo(List<MetasResumoDto> metasResumo) {
        this.metasResumo = metasResumo;
    }
}
