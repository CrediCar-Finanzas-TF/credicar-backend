package com.credicar.backend.catalog.domain.exceptions;

public class VehicleNotFoundException extends RuntimeException {

    public VehicleNotFoundException(String businessId) {
        super("Vehicle with business ID '%s' was not found".formatted(businessId));
    }
}
