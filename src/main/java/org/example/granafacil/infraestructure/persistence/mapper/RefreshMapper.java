package org.example.granafacil.infraestructure.persistence.mapper;

import org.example.granafacil.core.domain.entities.RefreshToken;
import org.example.granafacil.infraestructure.persistence.entites.RefreshTokenEntity;
import org.mapstruct.Mapper;


@Mapper(
        componentModel = "spring"
)
public interface RefreshMapper {

    RefreshToken toDomain (RefreshTokenEntity entity);
    RefreshTokenEntity toEntity (RefreshToken domain);
}
