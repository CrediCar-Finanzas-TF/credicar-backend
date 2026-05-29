package com.credicar.backend.crm.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record DocumentId(
        @Column(nullable = false, length = 20) String documentType,
        @Column(nullable = false, length = 20) String documentNumber
) {
}
