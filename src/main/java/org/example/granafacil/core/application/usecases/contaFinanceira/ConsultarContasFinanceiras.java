package org.example.granafacil.core.application.usecases.contaFinanceira;

import org.example.granafacil.core.application.dtos.ContaFinanceiraDtos.ContaFinanceiraResponse;
import org.example.granafacil.core.application.gateways.ContaFinanceiraRepository;

import java.util.*;

public class ConsultarContasFinanceiras {
    private final ContaFinanceiraRepository contaFinanceiraRepository;


    public ConsultarContasFinanceiras(ContaFinanceiraRepository contaFinanceiraRepository) {
        this.contaFinanceiraRepository = contaFinanceiraRepository;

    }


    public List<ContaFinanceiraResponse> execute(Long usuarioId){

        return contaFinanceiraRepository.findAllAccountByUsuarioIdToResponse(usuarioId);


    }

}
