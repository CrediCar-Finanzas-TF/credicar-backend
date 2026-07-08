package com.credicar.backend.catalog.interfaces.rest.resources;

import java.math.BigDecimal;

public record VehicleResource(
        Long id,
        String businessId,
        String brand,
        String model,
        String version,
        String imageUrl,
        String engine,
        String transmission,
        String combinedPower,
        String traction,
        String vehicleType,
        BigDecimal priceAmount,
        String priceCurrency,
        int stock,
        String location) {
}
