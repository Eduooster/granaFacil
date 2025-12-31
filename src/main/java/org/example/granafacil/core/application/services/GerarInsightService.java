package org.example.granafacil.core.application.services;

import org.example.granafacil.core.application.dtos.InsightDto;
import org.springframework.stereotype.Service;


@Service
public class GerarInsightService {


     public InsightDto gerar() {
        return new InsightDto();
    }
}
