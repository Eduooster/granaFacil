package org.example.granafacil.infraestructure.persistence.mapper;

import org.example.granafacil.core.domain.entities.ContaFinanceira;
import org.example.granafacil.infraestructure.persistence.entites.ContaFinanceiraEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContaFinanceiraMapper {


    ContaFinanceira toDomain (ContaFinanceiraEntity contaFinanceiraEntity);
    ContaFinanceiraEntity toEntity (ContaFinanceira contaFinanceira);
}
