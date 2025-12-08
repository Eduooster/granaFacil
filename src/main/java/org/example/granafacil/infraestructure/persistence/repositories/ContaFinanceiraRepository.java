package org.example.granafacil.infraestructure.persistence.repositories;

import org.example.granafacil.infraestructure.persistence.entites.ContaFinanceiraEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaFinanceiraRepository extends JpaRepository<ContaFinanceiraEntity, Long> {
}
