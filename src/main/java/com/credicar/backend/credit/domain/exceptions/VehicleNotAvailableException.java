package com.credicar.backend.credit.domain.exceptions;

public class VehicleNotAvailableException extends RuntimeException {

    public VehicleNotAvailableException(Long vehicleId) {
        super("Vehicle with ID '%d' was not found or is not available".formatted(vehicleId));
    }
}
