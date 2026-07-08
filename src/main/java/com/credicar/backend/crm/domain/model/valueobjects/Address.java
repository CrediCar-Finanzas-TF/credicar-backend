package com.credicar.backend.crm.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record Address(
        @Column(nullable = false) String streetAddress
) {
}
