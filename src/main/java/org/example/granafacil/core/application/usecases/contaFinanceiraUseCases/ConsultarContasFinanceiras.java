package org.example.granafacil.core.application.usecases.contaFinanceiraUseCases;

import org.example.granafacil.core.application.dtos.ContaFinanceiraResponse;
import org.example.granafacil.core.application.gateways.ConexaoOpenFinanceRepository;
import org.example.granafacil.core.application.gateways.ContaFinanceiraRepository;
import org.example.granafacil.core.domain.entities.ContaFinanceira;

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
