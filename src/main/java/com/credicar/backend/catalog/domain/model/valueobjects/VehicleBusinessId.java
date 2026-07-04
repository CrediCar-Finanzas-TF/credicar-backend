package com.credicar.backend.catalog.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record VehicleBusinessId(String code) {

    public VehicleBusinessId {
        if (code == null || code.isBlank())
            throw new IllegalArgumentException("Vehicle business ID code cannot be blank");
    }
}
