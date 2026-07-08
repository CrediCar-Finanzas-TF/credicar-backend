package com.credicar.backend.crm.infrastructure.persistence.jpa.repositories;

import com.credicar.backend.crm.domain.model.aggregates.Client;
import com.credicar.backend.crm.domain.model.valueobjects.DocumentId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByDocumentId(DocumentId documentId);
    boolean existsByDocumentId(DocumentId documentId);
    Optional<Client> findByDocumentIdDocumentNumber(String documentNumber);

    @Query("""
            SELECT c FROM Client c WHERE
            LOWER(c.personName.firstName)        LIKE LOWER(CONCAT('%', :search, '%')) OR
            LOWER(c.personName.lastName)         LIKE LOWER(CONCAT('%', :search, '%')) OR
            c.documentId.documentNumber          LIKE CONCAT('%', :search, '%')
            """)
    Page<Client> searchByText(@Param("search") String search, Pageable pageable);
}
