package com.credicar.backend.credit.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record InterestRate(String type, Double percentage, String capitalization) {
}
