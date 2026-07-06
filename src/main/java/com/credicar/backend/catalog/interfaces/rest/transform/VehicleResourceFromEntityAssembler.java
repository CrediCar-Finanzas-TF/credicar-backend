package com.credicar.backend.catalog.interfaces.rest.transform;

import com.credicar.backend.catalog.domain.model.aggregates.Vehicle;
import com.credicar.backend.catalog.interfaces.rest.resources.VehicleResource;

public class VehicleResourceFromEntityAssembler {

    public static VehicleResource toResourceFromEntity(Vehicle vehicle) {
        return new VehicleResource(
                vehicle.getId(),
                vehicle.getVehicleBusinessId().code(),
                vehicle.getVehicleDetails().brand(),
                vehicle.getVehicleDetails().model(),
                vehicle.getVehicleDetails().version(),
                vehicle.getVehicleDetails().imageUrl(),
                vehicle.getTechnicalSpecs().engine(),
                vehicle.getTechnicalSpecs().transmission(),
                vehicle.getTechnicalSpecs().combinedPower(),
                vehicle.getTechnicalSpecs().traction(),
                vehicle.getTechnicalSpecs().vehicleType(),
                vehicle.getPrice().amount(),
                vehicle.getPrice().currency()
        );
    }
}
