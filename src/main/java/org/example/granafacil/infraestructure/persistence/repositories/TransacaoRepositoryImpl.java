package org.example.granafacil.infraestructure.persistence.repositories;

import org.example.granafacil.infraestructure.persistence.entites.TransacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepositoryImpl extends JpaRepository<TransacaoEntity, Long> {
    TransacaoEntity findByContaIdAndPluggyId(String contaId, String pluggyId);
}