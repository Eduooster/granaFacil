package org.example.granafacil.infraestructure.presentation.dto.UsuarioDto;

import org.example.granafacil.core.domain.enums.FormaGerenciarFinancas;
import org.example.granafacil.core.domain.enums.ObjetivoFinanceiro;
import org.example.granafacil.core.domain.enums.PerfilFinanceiro;

public record UsuarioDetailResponseDto(
        Long id,
        String nome, String sobrenome, String email, String senha,
        ObjetivoFinanceiro objetivo,
        FormaGerenciarFinancas financas,
        PerfilFinanceiro perfil
) {
}
