package org.example.granafacil.infraestructure.persistence.mapper;

import org.example.granafacil.core.domain.entities.ContaFinanceira;
import org.example.granafacil.infraestructure.persistence.entites.ContaFinanceiraEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {UsuarioEntityMapper.class,InstituicaEntityMapper.class,ConexaoOpenFinanceEntityMapper.class})
public interface ContaFinanceiraMapper {


    ContaFinanceira toDomain (ContaFinanceiraEntity contaFinanceiraEntity);
    @Mapping(target = "usuario", source = "usuario")

    ContaFinanceiraEntity toEntity (ContaFinanceira contaFinanceira);
}
