package org.example.granafacil.core.application.dtos;

public class InsightDto {
    private InsightTipo tipo;
    private String mensagem;

    private static enum InsightTipo {
        POSITIVO, NEGATIVO,NEUTRO
    }
}
