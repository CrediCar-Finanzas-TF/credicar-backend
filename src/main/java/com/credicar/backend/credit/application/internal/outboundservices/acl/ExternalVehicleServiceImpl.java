package com.credicar.backend.credit.application.internal.outboundservices.acl;

import com.credicar.backend.catalog.interfaces.acl.CatalogContextFacade;
import org.springframework.stereotype.Service;

@Service
public class ExternalVehicleServiceImpl implements ExternalVehicleService {

    private final CatalogContextFacade catalogContextFacade;

    public ExternalVehicleServiceImpl(CatalogContextFacade catalogContextFacade) {
        this.catalogContextFacade = catalogContextFacade;
    }

    @Override
    public boolean existsVehicleById(Long vehicleId) {
        return catalogContextFacade.existsVehicleById(vehicleId);
    }
}
