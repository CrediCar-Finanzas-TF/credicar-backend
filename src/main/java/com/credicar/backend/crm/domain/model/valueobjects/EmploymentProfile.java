package com.credicar.backend.crm.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record EmploymentProfile(
        @Column(nullable = false) String company,
        @Column(nullable = false) Double monthlyIncome,
        @Column(nullable = false) Integer seniorityYears
) {
}
