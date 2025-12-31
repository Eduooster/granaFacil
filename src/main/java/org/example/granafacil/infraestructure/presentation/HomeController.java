package org.example.granafacil.infraestructure.presentation;

import org.example.granafacil.core.application.dtos.ResumoMensalResponse;
import org.example.granafacil.core.application.usecases.resumo.ObterResumoMensalUseCase;
import org.example.granafacil.infraestructure.persistence.entites.UsuarioEntity;
import org.example.granafacil.infraestructure.persistence.mapper.UsuarioEntityMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.YearMonth;

@RestController
@RequestMapping("home")
public class HomeController {

    private final ObterResumoMensalUseCase obterResumoMensalUseCase;
    private final UsuarioEntityMapper usuarioEntityMapper;

    public HomeController(ObterResumoMensalUseCase obterResumoMensalUseCase, UsuarioEntityMapper usuarioEntityMapper) {
        this.obterResumoMensalUseCase = obterResumoMensalUseCase;
        this.usuarioEntityMapper = usuarioEntityMapper;
    }

    @GetMapping("resumo-mensal")
    public ResponseEntity<ResumoMensalResponse> getResumoMensal(@RequestParam String mes, @AuthenticationPrincipal UsuarioEntity usuario) {

        YearMonth ym = YearMonth.parse(mes);

        return ResponseEntity.ok(obterResumoMensalUseCase.execute(ym,usuarioEntityMapper.toDomain(usuario)));
    }
}
