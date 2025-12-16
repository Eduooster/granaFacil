package org.example.granafacil.core.application.services;

import org.example.granafacil.core.domain.enums.CategoriaInterna;

import java.util.Map;
import java.util.HashMap;

public class CategoriaService {

    public static final Map<String, CategoriaInterna> pluggyToInterna = new HashMap<>();

    static {

        pluggyToInterna.put("01010000", CategoriaInterna.SALARIO);
        pluggyToInterna.put("01050000", CategoriaInterna.OUTRAS_RECEITAS);



        pluggyToInterna.put("17010000", CategoriaInterna.ALUGUEL);
        pluggyToInterna.put("17020001", CategoriaInterna.AGUA);
        pluggyToInterna.put("17020002", CategoriaInterna.LUZ);
        pluggyToInterna.put("17020003", CategoriaInterna.GAS);
        pluggyToInterna.put("17030000", CategoriaInterna.MANUTENCAO_RESIDENCIA);


        pluggyToInterna.put("19050005", CategoriaInterna.MANUTENCAO_VEICULO);
        pluggyToInterna.put("19050002", CategoriaInterna.ESTACIONAMENTO);
        pluggyToInterna.put("19050003", CategoriaInterna.PEDAGIO);


        pluggyToInterna.put("18020000", CategoriaInterna.FARMACIA);


        pluggyToInterna.put("21000000", CategoriaInterna.LAZER);


        pluggyToInterna.put("03000000", CategoriaInterna.INVESTIMENTOS);


        pluggyToInterna.put("15010000", CategoriaInterna.JUROS);
        pluggyToInterna.put("16000000", CategoriaInterna.TARIFAS_BANCARIAS);

        pluggyToInterna.put("99999999", CategoriaInterna.OUTRAS_DESPESAS);
    }
}
