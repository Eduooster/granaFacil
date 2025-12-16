package org.example.granafacil.infraestructure.presentation;

import org.example.granafacil.core.application.usecases.UsuarioUseCases.AtualizarFormaGerenciamentoFinancasUsuario;
import org.example.granafacil.core.application.usecases.UsuarioUseCases.AtualizarObjetivoFinanceirolUsuario;
import org.example.granafacil.core.application.usecases.UsuarioUseCases.AtualizarPerfilFinanceiroUsuario;
import org.example.granafacil.core.application.usecases.UsuarioUseCases.PegarDadosUsuario;
import org.example.granafacil.core.domain.entities.Usuario;
import org.example.granafacil.infraestructure.persistence.entites.UsuarioEntity;
import org.example.granafacil.infraestructure.persistence.mapper.UsuarioEntityMapper;
import org.example.granafacil.infraestructure.presentation.dto.UsuarioDto.DefinirFormaGerenciamentorDto;
import org.example.granafacil.infraestructure.presentation.dto.UsuarioDto.DefinirObjetivoFinanceiroDto;
import org.example.granafacil.infraestructure.presentation.dto.UsuarioDto.DefinirPerfilFinanceiroDto;
import org.example.granafacil.infraestructure.security.TokenServiceAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/usuarios")
@RestController
public class UsuarioController {


    private final AtualizarFormaGerenciamentoFinancasUsuario atualizarFormaGerenciamentoFinancasUsuario;
    private final AtualizarPerfilFinanceiroUsuario atualizarPerfilFinanceiroUsuario;
    private final AtualizarObjetivoFinanceirolUsuario atualizarObjetivoFinanceirolUsuario;
    private final TokenServiceAdapter tokenServiceAdapter;
    private final PegarDadosUsuario pegarDadosUsuario;
    private final UsuarioEntityMapper usuarioEntityMapper;


    public UsuarioController(AtualizarFormaGerenciamentoFinancasUsuario atualizarFormaGerenciamentoFinancasUsuario, AtualizarPerfilFinanceiroUsuario atualizarPerfilFinanceiroUsuario, AtualizarObjetivoFinanceirolUsuario atualizarObjetivoFinanceirolUsuario, TokenServiceAdapter tokenServiceAdapter, PegarDadosUsuario pegarDadosUsuario, UsuarioEntityMapper usuarioEntityMapper) {
        this.atualizarFormaGerenciamentoFinancasUsuario = atualizarFormaGerenciamentoFinancasUsuario;
        this.atualizarPerfilFinanceiroUsuario = atualizarPerfilFinanceiroUsuario;
        this.atualizarObjetivoFinanceirolUsuario = atualizarObjetivoFinanceirolUsuario;
        this.tokenServiceAdapter = tokenServiceAdapter;
        this.pegarDadosUsuario = pegarDadosUsuario;
        this.usuarioEntityMapper = usuarioEntityMapper;
    }

    @PatchMapping("/perfil-financeiro")
    public ResponseEntity atualizarPerfilFinanceiro(@AuthenticationPrincipal UsuarioEntity usuario, @RequestBody DefinirPerfilFinanceiroDto definirPerfilFinanceiroDto) {


        atualizarPerfilFinanceiroUsuario.execute(usuarioEntityMapper.toDomain(usuario),definirPerfilFinanceiroDto.perfil());

        return ResponseEntity.noContent().build();

    }

    @PatchMapping("/objetivo-financeiro")
    public ResponseEntity atualizarObjetivoFinanceiro(@AuthenticationPrincipal  UsuarioEntity usuario, @RequestBody DefinirObjetivoFinanceiroDto definirObjetivoFinanceiroDto) {
        atualizarObjetivoFinanceirolUsuario.execute(usuarioEntityMapper.toDomain(usuario),definirObjetivoFinanceiroDto.objetivoFinanceiro());
        return ResponseEntity.noContent().build();

    }
    @PatchMapping("/forma-gerencimento")
    public ResponseEntity atualizarFormaGerenciamento(@AuthenticationPrincipal  UsuarioEntity usuario, @RequestBody DefinirFormaGerenciamentorDto definirFormaGerenciamentorDto) {
        atualizarFormaGerenciamentoFinancasUsuario.execute(usuarioEntityMapper.toDomain(usuario),definirFormaGerenciamentorDto.formaGerenciarFinancas());
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/me")
    public ResponseEntity getMe(@AuthenticationPrincipal UsuarioEntity usuario) {
        return ResponseEntity.ok(pegarDadosUsuario.execute(usuario.getId()));
    }


}

