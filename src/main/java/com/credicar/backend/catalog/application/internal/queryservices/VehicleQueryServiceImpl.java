package com.credicar.backend.catalog.application.internal.queryservices;

import com.credicar.backend.catalog.domain.model.aggregates.Vehicle;
import com.credicar.backend.catalog.domain.model.queries.GetAllVehiclesQuery;
import com.credicar.backend.catalog.domain.model.queries.GetVehicleByBusinessIdQuery;
import com.credicar.backend.catalog.domain.model.valueobjects.VehicleBusinessId;
import com.credicar.backend.catalog.domain.services.VehicleQueryService;
import com.credicar.backend.catalog.infrastructure.persistence.jpa.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleQueryServiceImpl implements VehicleQueryService {

    private final VehicleRepository vehicleRepository;

    public VehicleQueryServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public List<Vehicle> handle(GetAllVehiclesQuery query) {
        return vehicleRepository.findAll();
    }

    @Override
    public Optional<Vehicle> handle(GetVehicleByBusinessIdQuery query) {
        return vehicleRepository.findByVehicleBusinessId(new VehicleBusinessId(query.businessId()));
    }
}
