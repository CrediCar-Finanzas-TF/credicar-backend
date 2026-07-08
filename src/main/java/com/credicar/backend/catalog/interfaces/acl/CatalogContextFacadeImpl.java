package com.credicar.backend.catalog.interfaces.acl;

import com.credicar.backend.catalog.infrastructure.persistence.jpa.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

@Service
public class CatalogContextFacadeImpl implements CatalogContextFacade {

    private final VehicleRepository vehicleRepository;

    public CatalogContextFacadeImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public boolean existsVehicleById(Long vehicleId) {
        return vehicleRepository.existsById(vehicleId);
    }
}
