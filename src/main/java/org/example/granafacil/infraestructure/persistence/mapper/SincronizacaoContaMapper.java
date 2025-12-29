package org.example.granafacil.infraestructure.persistence.mapper;


import org.example.granafacil.core.domain.entities.SincronizacaoConta;
import org.example.granafacil.infraestructure.persistence.entites.SincronizacaoContaEntity;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",uses = {
                ConexaoOpenFinanceEntityMapper.class,
        ContaFinanceiraMapper.class
}
)
public interface SincronizacaoContaMapper {

    SincronizacaoContaEntity toEntity(SincronizacaoConta sincronizacaoConta);
    SincronizacaoConta toDomain(SincronizacaoContaEntity sincronizacaoContaEntity);
}
