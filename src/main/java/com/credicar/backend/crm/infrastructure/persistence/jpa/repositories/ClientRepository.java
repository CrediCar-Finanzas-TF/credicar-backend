package com.credicar.backend.crm.infrastructure.persistence.jpa.repositories;

import com.credicar.backend.crm.domain.model.aggregates.Client;
import com.credicar.backend.crm.domain.model.valueobjects.DocumentId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByDocumentId(DocumentId documentId);
    boolean existsByDocumentId(DocumentId documentId);
    Optional<Client> findByDocumentIdDocumentNumber(String documentNumber);
}
