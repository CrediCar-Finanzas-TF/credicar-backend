package com.credicar.backend.catalog.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record TechnicalSpecs(
        String engine,
        String transmission,
        String combinedPower,
        String traction,
        String vehicleType) {
}
