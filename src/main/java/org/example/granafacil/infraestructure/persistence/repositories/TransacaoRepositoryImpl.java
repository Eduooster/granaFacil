package org.example.granafacil.infraestructure.persistence.repositories;

import org.example.granafacil.core.domain.entities.Transacao;
import org.example.granafacil.infraestructure.persistence.entites.TransacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransacaoRepositoryImpl extends JpaRepository<TransacaoEntity, Long> {




    Optional<Transacao> findByContaIdAndExternalTransactionId(Long contaId, String externalTransactionId);
}