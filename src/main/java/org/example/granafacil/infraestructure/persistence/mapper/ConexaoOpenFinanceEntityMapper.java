package org.example.granafacil.infraestructure.persistence.mapper;

import org.example.granafacil.core.domain.entities.ConexaoOpenFinance;
import org.example.granafacil.infraestructure.persistence.entites.ConexaoOpenFinanceEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = {
        UsuarioEntityMapper.class,
        InstituicaEntityMapper.class
})
public interface ConexaoOpenFinanceEntityMapper {

    ConexaoOpenFinanceEntity toEntity(ConexaoOpenFinance conexaoOpenFinance);
    ConexaoOpenFinance toDomain(ConexaoOpenFinanceEntity conexaoOpenFinanceEntity);
}
