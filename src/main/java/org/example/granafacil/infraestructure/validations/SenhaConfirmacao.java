package org.example.granafacil.infraestructure.validations;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = SenhaConfirmacaoValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface SenhaConfirmacao {
    String message() default "Senha e confirmação da senha não conferem";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
