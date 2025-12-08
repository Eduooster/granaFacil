package org.example.granafacil.infraestructure.persistence.mapper;

import org.example.granafacil.core.domain.entities.InstituicaoFinanceira;
import org.example.granafacil.infraestructure.persistence.entites.InstituicaoFinanceiraEntity;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface InstituicaEntityMapper {

    InstituicaoFinanceira toDomain (InstituicaoFinanceiraEntity instituicaoFinanceiraEntity);
    InstituicaoFinanceiraEntity toEntity(InstituicaoFinanceira instituicaoFinanceira);
}
