package org.example.granafacil.infraestructure.persistence.adapters;

import org.example.granafacil.core.application.gateways.UsuarioRepository;
import org.example.granafacil.core.domain.entities.Usuario;
import org.example.granafacil.core.domain.enums.FormaGerenciarFinancas;
import org.example.granafacil.core.domain.enums.ObjetivoFinanceiro;
import org.example.granafacil.core.domain.enums.PerfilFinanceiro;
import org.example.granafacil.infraestructure.persistence.entites.UsuarioEntity;
import org.example.granafacil.infraestructure.persistence.mapper.UsuarioEntityMapper;
import org.example.granafacil.infraestructure.persistence.repositories.UsuarioRepositoryImpl;
import org.example.granafacil.infraestructure.presentation.AutenticacaoController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JpaUsuarioAdapter implements UsuarioRepository {

    private final UsuarioRepositoryImpl usuarioRepositoryImpl;

    private static final Logger log = LoggerFactory.getLogger(AutenticacaoController.class);
    private final UsuarioEntityMapper usuarioEntityMapper;

    public JpaUsuarioAdapter(UsuarioRepositoryImpl usuarioRepositoryImpl, UsuarioEntityMapper usuarioEntityMapper) {
        this.usuarioRepositoryImpl = usuarioRepositoryImpl;

        this.usuarioEntityMapper = usuarioEntityMapper;
    }

    @Override
    public void save(Usuario usuario) {
        UsuarioEntity usuarioEntity = usuarioEntityMapper.toEntity(usuario);
        usuarioRepositoryImpl.save(usuarioEntity);
    }

    @Override
    public Usuario criar(Usuario usuario) {
        UsuarioEntity usuarioEntity = usuarioEntityMapper.toEntity(usuario);
        return usuarioEntityMapper.toDomain(usuarioRepositoryImpl.save(usuarioEntity));
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepositoryImpl.findByEmail(email)
                .map(usuarioEntity -> usuarioEntityMapper.toDomain(usuarioEntity));

    }

    @Override
    public Usuario getMe(Long idUsario) {
        Optional<Usuario> usuarioRepo = usuarioRepositoryImpl.findById(idUsario)
                .map(usuarioEntity -> usuarioEntityMapper.toDomain(usuarioEntity));

        if (usuarioRepo.isPresent()) {
            return usuarioRepo.get();
        }
        return null;
    }


    @Override
    public Usuario buscaIdUsuario(Long id) {
        Optional<Usuario> usuarioEntity =  usuarioRepositoryImpl.findById(id)
                .map(ue -> usuarioEntityMapper.toDomain(ue));

        if (usuarioEntity.isPresent() ) {
            return usuarioEntity.get();
        }
        return null;
    }

    @Override
    public void atualizarObjetivo(Usuario usuario, ObjetivoFinanceiro objetivo) {
        usuario.setObjetivo(objetivo);
        usuarioRepositoryImpl.save(usuarioEntityMapper.toEntity(usuario));

    }

    @Override
    public void atualizarFormaGerenciarFinancas(Usuario usuario, FormaGerenciarFinancas forma) {
        usuario.setFinancas(forma);
        usuarioRepositoryImpl.save(usuarioEntityMapper.toEntity(usuario));

    }

    @Override
    public void atualizarPerfilFinanceiro(Usuario usuario, PerfilFinanceiro perfil) {
        usuario.setPerfil(perfil);
        usuarioRepositoryImpl.save(usuarioEntityMapper.toEntity(usuario));

    }



}
