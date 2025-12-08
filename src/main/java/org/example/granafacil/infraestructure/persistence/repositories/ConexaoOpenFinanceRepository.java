package org.example.granafacil.infraestructure.persistence.repositories;

import org.example.granafacil.infraestructure.persistence.entites.ConexaoOpenFinanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConexaoOpenFinanceRepository extends JpaRepository<ConexaoOpenFinanceEntity, Long> {


    Optional<ConexaoOpenFinanceEntity> findByPluggyItemId(String pluggyItemId);

    Optional<ConexaoOpenFinanceEntity> findByPluggyItemIdAndAtivoTrue(String pluggyItemId);
}
