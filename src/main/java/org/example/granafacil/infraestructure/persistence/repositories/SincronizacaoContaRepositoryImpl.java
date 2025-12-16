package org.example.granafacil.infraestructure.persistence.repositories;

import org.example.granafacil.core.domain.enums.StatusSincronizacao;
import org.example.granafacil.infraestructure.persistence.entites.SincronizacaoContaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SincronizacaoContaRepositoryImpl extends JpaRepository<SincronizacaoContaEntity, Long> {
    List<SincronizacaoContaEntity> findConnectorIdsByStatusAtual(StatusSincronizacao atual);
}
