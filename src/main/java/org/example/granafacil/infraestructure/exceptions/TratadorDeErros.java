package org.example.granafacil.infraestructure.exceptions;

import org.example.granafacil.core.domain.exceptions.UsuarioNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class TratadorDeErros {


    public TratadorDeErros() {}

    @ExceptionHandler({UsuarioNaoEncontradoException.class})
    public ResponseEntity<Map<String, String>> UsuarioNaoEncontrado(UsuarioNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(this.detalhar(ex));
    }


    public Map<String, String> detalhar(Exception ex) {
        Map<String, String> map = new HashMap();
        map.put("erro", ex.getClass().getSimpleName());
        map.put("mensagem", ex.getMessage());
        return map;
    }
}
