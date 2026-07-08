package com.credicar.backend.credit.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record FinancialIndicators(Double van, Double tir, Double tcea) {
}
