package com.credicar.backend.credit.domain.model.valueobjects;

import com.credicar.backend.shared.domain.model.valueobjects.Money;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public record Insurances(Double desgravamenRate, @Embedded Money vehicularInsuranceMonthly) {
}
