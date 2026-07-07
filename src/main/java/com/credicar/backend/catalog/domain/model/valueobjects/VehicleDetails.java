package com.credicar.backend.catalog.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record VehicleDetails(String brand, String model, String version,
                              @Column(length = 2048) String imageUrl) {
}
