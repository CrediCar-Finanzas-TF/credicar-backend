package com.credicar.backend.catalog.application.internal.eventhandlers;

import com.credicar.backend.catalog.domain.model.commands.SeedVehiclesCommand;
import com.credicar.backend.catalog.domain.services.VehicleCommandService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class ApplicationReadyEventHandler {

    private final VehicleCommandService vehicleCommandService;

    public ApplicationReadyEventHandler(VehicleCommandService vehicleCommandService) {
        this.vehicleCommandService = vehicleCommandService;
    }

    @EventListener
    public void on(ApplicationReadyEvent event) {
        vehicleCommandService.handle(new SeedVehiclesCommand());
    }
}
