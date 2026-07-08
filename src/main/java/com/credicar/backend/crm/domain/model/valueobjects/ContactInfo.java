package com.credicar.backend.crm.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record ContactInfo(
        @Column(nullable = false, length = 20) String phone,
        @Column(nullable = false, unique = true) String email
) {
}
