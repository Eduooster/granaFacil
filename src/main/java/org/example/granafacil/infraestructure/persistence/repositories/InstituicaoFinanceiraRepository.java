package org.example.granafacil.infraestructure.persistence.repositories;


import org.example.granafacil.infraestructure.persistence.entites.InstituicaoFinanceiraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstituicaoFinanceiraRepository extends JpaRepository<InstituicaoFinanceiraEntity, Long> {
    Optional<InstituicaoFinanceiraEntity> findByIdConnector(Long connectorId);
}
