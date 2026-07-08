package com.credicar.backend.catalog.infrastructure.persistence.jpa.repositories;

import com.credicar.backend.catalog.domain.model.aggregates.Vehicle;
import com.credicar.backend.catalog.domain.model.valueobjects.VehicleBusinessId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Optional<Vehicle> findByVehicleBusinessId(VehicleBusinessId vehicleBusinessId);
    boolean existsByVehicleBusinessId(VehicleBusinessId vehicleBusinessId);
}
