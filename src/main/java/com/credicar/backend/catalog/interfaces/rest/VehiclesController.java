package com.credicar.backend.catalog.interfaces.rest;

import com.credicar.backend.catalog.domain.exceptions.VehicleNotFoundException;
import com.credicar.backend.catalog.domain.model.queries.GetAllVehiclesQuery;
import com.credicar.backend.catalog.domain.model.queries.GetVehicleByBusinessIdQuery;
import com.credicar.backend.catalog.domain.services.VehicleQueryService;
import com.credicar.backend.catalog.interfaces.rest.resources.VehicleResource;
import com.credicar.backend.catalog.interfaces.rest.transform.VehicleResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicles")
@Tag(name = "Vehicles", description = "Vehicle catalog management endpoints")
public class VehiclesController {

    private final VehicleQueryService vehicleQueryService;

    public VehiclesController(VehicleQueryService vehicleQueryService) {
        this.vehicleQueryService = vehicleQueryService;
    }

    @GetMapping
    public ResponseEntity<List<VehicleResource>> getAllVehicles() {
        var resources = vehicleQueryService.handle(new GetAllVehiclesQuery())
                .stream()
                .map(VehicleResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{businessId}")
    public ResponseEntity<VehicleResource> getVehicleByBusinessId(@PathVariable String businessId) {
        return vehicleQueryService.handle(new GetVehicleByBusinessIdQuery(businessId))
                .map(VehicleResourceFromEntityAssembler::toResourceFromEntity)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new VehicleNotFoundException(businessId));
    }
}
