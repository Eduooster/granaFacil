package org.example.granafacil.infraestructure.persistence.repositories;

import org.example.granafacil.infraestructure.persistence.entites.TransacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<TransacaoEntity, Long> {
}
