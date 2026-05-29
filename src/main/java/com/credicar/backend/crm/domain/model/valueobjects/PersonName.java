package com.credicar.backend.crm.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record PersonName(
        @Column(nullable = false) String firstName,
        @Column(nullable = false) String lastName
) {
}
