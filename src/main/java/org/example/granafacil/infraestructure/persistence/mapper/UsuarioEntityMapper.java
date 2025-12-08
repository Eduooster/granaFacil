package org.example.granafacil.infraestructure.persistence.mapper;

import org.example.granafacil.core.domain.entities.Usuario;
import org.example.granafacil.infraestructure.persistence.entites.UsuarioEntity;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface UsuarioEntityMapper {

    Usuario toDomain (UsuarioEntity usuarioEntity);
    UsuarioEntity toEntity (Usuario usuario);

}
