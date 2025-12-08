package org.example.granafacil.infraestructure.persistence.adapters;

import org.example.granafacil.core.application.gateways.UsuarioGateway;
import org.example.granafacil.core.domain.entities.Usuario;
import org.example.granafacil.core.domain.enums.FormaGerenciarFinancas;
import org.example.granafacil.core.domain.enums.ObjetivoFinanceiro;
import org.example.granafacil.core.domain.enums.PerfilFinanceiro;
import org.example.granafacil.infraestructure.persistence.entites.UsuarioEntity;
import org.example.granafacil.infraestructure.persistence.repositories.UsuarioRepository;
import org.example.granafacil.infraestructure.presentation.AutenticacaoController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JpaUsuarioAdapter implements UsuarioGateway {

    private final UsuarioRepository usuarioRepository;

    private static final Logger log = LoggerFactory.getLogger(AutenticacaoController.class);

    public JpaUsuarioAdapter(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;

    }

    @Override
    public void save(Usuario usuario) {
        UsuarioEntity usuarioEntity = toEntity(usuario);
        usuarioRepository.save(usuarioEntity);
    }

    @Override
    public Usuario criar(Usuario usuario) {
        UsuarioEntity usuarioEntity = toEntity(usuario);
        return toDomain(usuarioRepository.save(usuarioEntity));
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .map(usuarioEntity -> toDomain(usuarioEntity));

    }



    @Override
    public Usuario buscaIdUsuario(Long id) {
        Optional<Usuario> usuarioEntity =  usuarioRepository.findById(id)
                .map(ue -> toDomain(ue));

        if (usuarioEntity.isPresent() ) {
            return usuarioEntity.get();
        }
        return null;
    }

    @Override
    public void atualizarObjetivo(Usuario usuario, ObjetivoFinanceiro objetivo) {
        usuario.setObjetivo(objetivo);
        usuarioRepository.save(toEntity(usuario));

    }

    @Override
    public void atualizarFormaGerenciarFinancas(Usuario usuario, FormaGerenciarFinancas forma) {
        usuario.setFinancas(forma);
        usuarioRepository.save(toEntity(usuario));

    }

    @Override
    public void atualizarPerfilFinanceiro(Usuario usuario, PerfilFinanceiro perfil) {
        usuario.setPerfil(perfil);
        usuarioRepository.save(toEntity(usuario));

    }

    //ARRUMAR ISSO

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
