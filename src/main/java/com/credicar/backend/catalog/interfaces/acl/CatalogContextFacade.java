package com.credicar.backend.catalog.interfaces.acl;

/**
 * Anti-Corruption Layer facade for the Catalog bounded context.
 * Other bounded contexts must use this interface to query vehicle data.
 */
public interface CatalogContextFacade {
    boolean existsVehicleById(Long vehicleId);
}
