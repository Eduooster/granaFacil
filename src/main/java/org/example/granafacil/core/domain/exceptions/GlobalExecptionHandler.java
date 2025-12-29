package org.example.granafacil.core.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExecptionHandler {

    @ExceptionHandler(ConexaoOpenFinanceDuplicada.class)
    public ResponseEntity<Object> handleConexaoOpenFinanceDuplicada(ConexaoOpenFinanceDuplicada ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(detalhar(ex));
    }

    @ExceptionHandler(ConexaoOpenFinanceExpirou.class)
    public ResponseEntity<Object> handleConexaoOpenFinanceExpirou(ConexaoOpenFinanceExpirou ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(detalhar(ex));
    }

    @ExceptionHandler(ContaFinanceiraDuplicada.class)
    public ResponseEntity<Object> handleContaFinanceiraDuplicada(ContaFinanceiraDuplicada ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(detalhar(ex));
    }
    @ExceptionHandler(ItemJaRegistrado.class)
    public ResponseEntity<Object> handleItemJaRegistrado(ItemJaRegistrado ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(detalhar(ex));
    }
    @ExceptionHandler(SenhaInvalidaExeception.class)
    public ResponseEntity<Object> handleSenhaInvalidaExeception(SenhaInvalidaExeception ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(detalhar(ex));
    }
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Object> handleUnauthorizedExceptiona(UnauthorizedException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(detalhar(ex));
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<Object> handleUsuarioNaoEncontradoException(UsuarioNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(detalhar(ex));
    }





    public Map<String,String> detalhar(Exception ex) {
        Map<String,String> map = new HashMap<>();
        map.put("erro", ex.getClass().getSimpleName());
        map.put("mensagem", ex.getMessage());
        return map;
    }
}
