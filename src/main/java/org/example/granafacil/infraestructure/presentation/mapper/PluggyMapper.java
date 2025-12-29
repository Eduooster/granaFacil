package org.example.granafacil.infraestructure.presentation.mapper;

import org.example.granafacil.core.application.dtos.PluggyItemData;
import org.example.granafacil.infraestructure.presentation.dto.pluggyDto.CreateItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

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
    @Mapping(target = "dataCriacao", source = "item.createdAt")
    @Mapping(target = "ultimoSync", source = "item.lastUpdatedAt")


    PluggyItemData toDomain(CreateItemDto dto);


    // Conversão manual String → LocalDateTime
    default OffsetDateTime map(Instant instant) {
        if (instant == null) return null;
        return instant.atOffset(ZoneOffset.UTC);
    }
}
