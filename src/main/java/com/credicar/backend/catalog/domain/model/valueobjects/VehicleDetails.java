package com.credicar.backend.catalog.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record VehicleDetails(String brand, String model, String version, String imageUrl) {
}
