package org.example.granafacil.infraestructure.presentation;

import org.example.granafacil.core.application.usecases.UsuarioUseCases.AtualizarFormaGerenciamentoFinancasUsuario;
import org.example.granafacil.core.application.usecases.UsuarioUseCases.AtualizarObjetivoFinanceirolUsuario;
import org.example.granafacil.core.application.usecases.UsuarioUseCases.AtualizarPerfilFinanceiroUsuario;
import org.example.granafacil.core.domain.entities.Usuario;
import org.example.granafacil.infraestructure.persistence.entites.UsuarioEntity;
import org.example.granafacil.infraestructure.presentation.dto.UsuarioDto.DefinirFormaGerenciamentorDto;
import org.example.granafacil.infraestructure.presentation.dto.UsuarioDto.DefinirObjetivoFinanceiroDto;
import org.example.granafacil.infraestructure.presentation.dto.UsuarioDto.DefinirPerfilFinanceiroDto;
import org.example.granafacil.infraestructure.security.TokenServiceAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/usuarios")
@RestController
public class UsuarioController {

    private final AtualizarFormaGerenciamentoFinancasUsuario atualizarFormaGerenciamentoFinancasUsuario;
    private final AtualizarPerfilFinanceiroUsuario atualizarPerfilFinanceiroUsuario;
    private final AtualizarObjetivoFinanceirolUsuario atualizarObjetivoFinanceirolUsuario;
    private static final Logger log = LoggerFactory.getLogger(AutenticacaoController.class);
    private final TokenServiceAdapter tokenServiceAdapter;


    public UsuarioController(AtualizarFormaGerenciamentoFinancasUsuario atualizarFormaGerenciamentoFinancasUsuario, AtualizarPerfilFinanceiroUsuario atualizarPerfilFinanceiroUsuario, AtualizarObjetivoFinanceirolUsuario atualizarObjetivoFinanceirolUsuario, TokenServiceAdapter tokenServiceAdapter) {
        this.atualizarFormaGerenciamentoFinancasUsuario = atualizarFormaGerenciamentoFinancasUsuario;
        this.atualizarPerfilFinanceiroUsuario = atualizarPerfilFinanceiroUsuario;
        this.atualizarObjetivoFinanceirolUsuario = atualizarObjetivoFinanceirolUsuario;
        this.tokenServiceAdapter = tokenServiceAdapter;
    }

    @PatchMapping("/perfil-financeiro")
    public ResponseEntity atualizarPerfilFinanceiro(@AuthenticationPrincipal UsuarioEntity usuario, @RequestBody DefinirPerfilFinanceiroDto definirPerfilFinanceiroDto) {
        log.info(usuario.toString());

        atualizarPerfilFinanceiroUsuario.execute(toDomain(usuario),definirPerfilFinanceiroDto.perfil());

        return ResponseEntity.noContent().build();

    }

    @PatchMapping("/objetivo-financeiro")
    public ResponseEntity atualizarObjetivoFinanceiro(@AuthenticationPrincipal  UsuarioEntity usuario, @RequestBody DefinirObjetivoFinanceiroDto definirObjetivoFinanceiroDto) {
        atualizarObjetivoFinanceirolUsuario.execute(toDomain(usuario),definirObjetivoFinanceiroDto.objetivoFinanceiro());
        return ResponseEntity.noContent().build();

    }
    @PatchMapping("/forma-gerencimento")
    public ResponseEntity atualizarFormaGerenciamento(@AuthenticationPrincipal  UsuarioEntity usuario, @RequestBody DefinirFormaGerenciamentorDto definirFormaGerenciamentorDto) {
        atualizarFormaGerenciamentoFinancasUsuario.execute(toDomain(usuario),definirFormaGerenciamentorDto.formaGerenciarFinancas());
        return ResponseEntity.noContent().build();

    }

    public UsuarioEntity toEntity(Usuario usuario) {
        if (usuario == null) return null;
        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(usuario.getId());
        entity.setNome(usuario.getNome());
        entity.setSobrenome(usuario.getSobrenome());
        entity.setEmail(usuario.getEmail());
        entity.setSenhaHash(usuario.getSenhaHash());
        entity.setObjetivo(usuario.getObjetivo());
        entity.setFinancas(usuario.getFinancas());
        entity.setPerfil(usuario.getPerfil());
        return entity;
    }

    public Usuario toDomain(UsuarioEntity entity) {
        if (entity == null) return null;
        Usuario usuario = new Usuario();
        usuario.setId(entity.getId());
        usuario.setNome(entity.getNome());
        usuario.setSobrenome(entity.getSobrenome());
        usuario.setEmail(entity.getEmail());
        usuario.setSenhaHash(entity.getSenhaHash());
        usuario.setObjetivo(entity.getObjetivo());
        usuario.setFinancas(entity.getFinancas());
        usuario.setPerfil(entity.getPerfil());
        return usuario;
    }
}

