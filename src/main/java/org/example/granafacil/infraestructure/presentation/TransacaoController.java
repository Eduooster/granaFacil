package org.example.granafacil.infraestructure.presentation;


import org.example.granafacil.core.domain.entities.Transacao;
import org.example.granafacil.core.domain.entities.Usuario;
import org.example.granafacil.infraestructure.persistence.entites.UsuarioEntity;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transacoes")
public class TransacaoController {

//    @GetMapping
//    public ResponseEntity<TransacaoResponseDto> buscarTransacao(@AuthenticationPrincipal UsuarioEntity usuario) {
//
//
//    }
}
