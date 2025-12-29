package org.example.granafacil.infraestructure.persistence.mapper;


import org.example.granafacil.core.domain.entities.Transacao;
import org.example.granafacil.infraestructure.persistence.entites.TransacaoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(
        componentModel = "spring",uses = {
                ContaFinanceiraMapper.class
}
)
public interface TransacaoMapper {

    @Mapping(source = "contaId", target = "contaId")
    @Mapping(source = "externalTransactionId", target = "externalTransactionId")
    TransacaoEntity toEntity(Transacao domain);

    @Mapping(source = "contaId", target = "contaId")
    @Mapping(source = "externalTransactionId", target = "externalTransactionId")
    Transacao toDomain(TransacaoEntity entity);

    List<Transacao> toDomainList(List<TransacaoEntity> entities);

    List<TransacaoEntity> toEntityList(List<Transacao> entities);

}
