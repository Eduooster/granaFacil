package org.example.granafacil.infraestructure.presentation;

import org.example.granafacil.core.application.dtos.ContaFinanceiraResponse;
import org.example.granafacil.core.application.usecases.contaFinanceiraUseCases.ConsultarContasFinanceiras;
import org.example.granafacil.core.domain.entities.Usuario;
import org.example.granafacil.infraestructure.persistence.entites.UsuarioEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/conta-financeira")
public class ContaFinanceiraController {
    private final ConsultarContasFinanceiras consultarContasFinanceiras;


    public ContaFinanceiraController(ConsultarContasFinanceiras consultarContasFinanceiras) {
        this.consultarContasFinanceiras = consultarContasFinanceiras;
    }

    @GetMapping
    public ResponseEntity<List<ContaFinanceiraResponse>> contaFinanceiras(@AuthenticationPrincipal UsuarioEntity usuario) {


        return ResponseEntity.ok(consultarContasFinanceiras.execute(usuario.getId()));

    }
}
