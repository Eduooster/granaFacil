package org.example.granafacil.infraestructure.presentation.dto.UsuarioDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.example.granafacil.infraestructure.validations.SenhaConfirmacao;

@SenhaConfirmacao
public record UsuarioRegisterRequestDto(

        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotBlank(message = "O sobrenome é obrigatório")
        String sobrenome,

        @NotBlank(message = "O email é obrigatório")
        @Email(message = "O email informado é inválido")
        String email,

        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
        String senha,

        @NotBlank(message = "A confirmação da senha é obrigatória")
        String confirmarSenha

) {}
