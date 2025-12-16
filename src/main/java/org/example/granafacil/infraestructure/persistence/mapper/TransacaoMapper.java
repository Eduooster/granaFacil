package org.example.granafacil.infraestructure.persistence.mapper;


import org.example.granafacil.core.domain.entities.Transacao;
import org.example.granafacil.infraestructure.persistence.entites.TransacaoEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface TransacaoMapper {

    Transacao toDomain(TransacaoEntity entity);

    TransacaoEntity toEntity(Transacao domain);

    List<Transacao> toDomainList(List<TransacaoEntity> entities);

    List<TransacaoEntity> toEntityList(List<Transacao> entities);

}
