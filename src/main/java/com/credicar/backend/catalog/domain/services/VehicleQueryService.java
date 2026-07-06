package com.credicar.backend.catalog.domain.services;

import com.credicar.backend.catalog.domain.model.aggregates.Vehicle;
import com.credicar.backend.catalog.domain.model.queries.GetAllVehiclesQuery;
import com.credicar.backend.catalog.domain.model.queries.GetVehicleByBusinessIdQuery;

import java.util.List;
import java.util.Optional;

public interface VehicleQueryService {
    List<Vehicle> handle(GetAllVehiclesQuery query);
    Optional<Vehicle> handle(GetVehicleByBusinessIdQuery query);
}
