package org.example.granafacil.core.application.usecases.resumo;

import lombok.extern.slf4j.Slf4j;
import org.example.granafacil.infraestructure.persistence.projections.FinanceiroResumoProjection;
import org.example.granafacil.core.application.dtos.ContaFinanceiraDtos.FinanceiroResumoDto;
import org.example.granafacil.core.application.dtos.InsightDto;
import org.example.granafacil.core.application.dtos.ResumoMensalResponse;
import org.example.granafacil.core.application.dtos.TransacaoDtos.MovimentacoesDto;
import org.example.granafacil.core.application.gateways.TransacaoRepository;
import org.example.granafacil.core.application.services.GerarInsightService;
import org.example.granafacil.core.domain.entities.Usuario;
import org.example.granafacil.core.domain.enums.CategoriaInterna;
import org.example.granafacil.core.domain.enums.TipoMovimentacao;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.Instant;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class ObterResumoMensalUseCase {

    private final TransacaoRepository transacaoRepository;
    private final GerarInsightService gerarInsightService;
    //private final MetaRepository

    public ObterResumoMensalUseCase(TransacaoRepository transacaoRepository, GerarInsightService gerarInsightService) {
        this.transacaoRepository = transacaoRepository;
        this.gerarInsightService = gerarInsightService;
    }



    public ResumoMensalResponse execute(YearMonth mes, Usuario usuario) {

        Pageable pageabletop5 = PageRequest.of(
                0,
                5,
                Sort.by(Sort.Direction.DESC, "data")
        );


        List<Instant> datasConvertidas = converterYearMonth(mes);


        FinanceiroResumoProjection financeiroResumoProjection = transacaoRepository.calcularResumoFinanceiro(usuario.getId(),datasConvertidas.get(0),datasConvertidas.get(1));

        log.info("FinanceiroResumoProjection: {}", financeiroResumoProjection);

        FinanceiroResumoDto financeiroResumoDto = new FinanceiroResumoDto(
                financeiroResumoProjection.getReceita(),
                financeiroResumoProjection.getDespesa(),
                financeiroResumoProjection.getVariacao()


        );

        log.info("FinanceiroResumoDto: {}", financeiroResumoDto);

        List<MovimentacoesDto> movimentacoes = converterMovimentacaoProjectionPageToMovimentacaoDtoList(usuario.getId(),datasConvertidas.get(0),datasConvertidas.get(1),pageabletop5);

        log.info("movimentacoes: {}", movimentacoes);


        InsightDto insightDto = gerarInsightService.gerar();

        return new ResumoMensalResponse(
                financeiroResumoDto,
                null ,
                movimentacoes != null? movimentacoes: List.of(),
                null
        );

    }

    private List<MovimentacoesDto> converterMovimentacaoProjectionPageToMovimentacaoDtoList(Long id, Instant inicio, Instant fim, Pageable pageabletop5) {

        return transacaoRepository
                .buscarMovimentacoesRecentes( id,inicio,fim,pageabletop5)
                .stream()
                .map(
                        p ->
                                new MovimentacoesDto( p.getId(), p.getDescricao(), p.getValor(), TipoMovimentacao.valueOf(p.getTipoMovimentacao()), p.getData(), CategoriaInterna.valueOf(p.getCategoriaInterna()))) .toList();
    }

    private List<Instant> converterYearMonth(YearMonth mes) {

        ZoneId zone = ZoneId.of("America/Sao_Paulo");

        Instant inicio = mes
                .atDay(1)
                .atStartOfDay(zone)
                .toInstant();

        Instant fim = mes
                .atEndOfMonth()
                .atTime(23, 59, 59)
                .atZone(zone)
                .toInstant();

        return new ArrayList<>(Arrays.asList(inicio, fim));

    }
}
