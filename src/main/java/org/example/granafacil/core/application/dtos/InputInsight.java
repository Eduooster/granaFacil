package org.example.granafacil.core.application.dtos;

import java.time.Instant;

public record InputInsight(
        Long usuarioId,
        Instant inicio,
        Instant fim
) {
}
