package org.example.granafacil.infraestructure.presentation.dto.AutenticacaoDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioLoginRequestDto(

            @NotBlank(message = "O email é obrigatório")
            @Email(message = "O email informado é inválido")
            String email,

            @NotBlank(message = "A senha é obrigatória")
            String senha) {
}
