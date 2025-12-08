package org.example.granafacil.infraestructure.presentation.mapper;

import org.example.granafacil.core.application.dtos.PluggyItemData;
import org.example.granafacil.infraestructure.presentation.dto.pluggyDto.CreateItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface PluggyMapper {

    // --- Connector ---
    @Mapping(target = "connectorId", source = "item.connector.id")
    @Mapping(target = "connectorName", source = "item.connector.name")
    @Mapping(target = "connectorImageUrl", source = "item.connector.imageUrl")
    @Mapping(target = "connectorHealth", source = "item.connector.health.status")
    @Mapping(target = "connectorTypeBank", source = "item.connector.type")

    // --- Item ---
    @Mapping(target = "pluggyItemId", source = "item.id")
    @Mapping(target = "status", source = "item.status")

    // --- Datas ---
    @Mapping(target = "dataCriacao", expression = "java(parse(dto.getItem().getCreatedAt()))")
    @Mapping(target = "ultimoSync", expression = "java(parse(dto.getItem().getLastUpdatedAt()))")


    PluggyItemData toDomain(CreateItemDto dto);


    // Conversão manual String → LocalDateTime
    default LocalDateTime parse(String s) {
        if (s == null) return null;
        return LocalDateTime.parse(s);
    }
}
