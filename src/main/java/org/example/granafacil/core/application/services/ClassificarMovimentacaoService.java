package org.example.granafacil.core.application.services;

import org.example.granafacil.core.domain.enums.CategoriaInterna;
import org.example.granafacil.core.domain.enums.TipoMovimentacao;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class ClassificarMovimentacaoService {
    private final CategoriaService categoriaService;

    public ClassificarMovimentacaoService(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }


    public TipoMovimentacao classificar(BigDecimal amount, String categoryId) {

        Map<String, CategoriaInterna> pluggyToInterna = categoriaService.pluggyToInterna;

        CategoriaInterna categoria = pluggyToInterna.get(categoryId);

        if (categoria != null) {
            switch (categoria) {
                case SALARIO:
                    return TipoMovimentacao.RECEITA;

                case TRANSFERENCIA_PIX:
                case TRANSFERENCIA_TED:
                case TRANSFERENCIA_MESMA_PESSOA:
                    return TipoMovimentacao.TRANSFERENCIA;

                default:
                    break;
            }
        }


        return amount.signum() < 0
                ? TipoMovimentacao.DESPESA
                : TipoMovimentacao.RECEITA;
    }

}
