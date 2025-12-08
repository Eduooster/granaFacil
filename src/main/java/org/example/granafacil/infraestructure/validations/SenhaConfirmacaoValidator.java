package org.example.granafacil.infraestructure.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.granafacil.infraestructure.presentation.dto.UsuarioDto.UsuarioRegisterRequestDto;

public class SenhaConfirmacaoValidator implements ConstraintValidator<SenhaConfirmacao, UsuarioRegisterRequestDto> {

    @Override
    public boolean isValid(UsuarioRegisterRequestDto dto, ConstraintValidatorContext context) {
        if (dto.senha() == null || dto.confirmarSenha() == null) {
            return false;
        }
        return dto.senha().equals(dto.confirmarSenha());
    }
}