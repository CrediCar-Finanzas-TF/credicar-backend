package com.credicar.backend.catalog.domain.services;

import com.credicar.backend.catalog.domain.model.commands.SeedVehiclesCommand;

public interface VehicleCommandService {
    void handle(SeedVehiclesCommand command);
}
