package com.credicar.backend.credit.infrastructure.persistence.jpa.repositories;

import com.credicar.backend.credit.domain.model.aggregates.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuotationRepository extends JpaRepository<Quotation, Long> {
    List<Quotation> findAllByClientId(Long clientId);
}
