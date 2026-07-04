package com.credicar.backend.catalog.application.internal.commandservices;

import com.credicar.backend.catalog.domain.model.aggregates.Vehicle;
import com.credicar.backend.catalog.domain.model.commands.SeedVehiclesCommand;
import com.credicar.backend.catalog.domain.model.valueobjects.TechnicalSpecs;
import com.credicar.backend.catalog.domain.model.valueobjects.VehicleBusinessId;
import com.credicar.backend.catalog.domain.model.valueobjects.VehicleDetails;
import com.credicar.backend.catalog.domain.services.VehicleCommandService;
import com.credicar.backend.catalog.infrastructure.persistence.jpa.repositories.VehicleRepository;
import com.credicar.backend.shared.domain.model.valueobjects.Money;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class VehicleCommandServiceImpl implements VehicleCommandService {

    private final VehicleRepository vehicleRepository;

    public VehicleCommandServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public void handle(SeedVehiclesCommand command) {
        if (vehicleRepository.count() > 0) return;

        var vehicles = List.of(
            new Vehicle(
                new VehicleBusinessId("V-SUV-001"),
                new VehicleDetails("Toyota", "RAV4", "2.5L Hybrid XSE AWD", "https://example.com/images/toyota-rav4.jpg"),
                new TechnicalSpecs("2.5L 4-Cylinder Hybrid", "CVT Automatic", "219 HP", "AWD", "SUV"),
                new Money(new BigDecimal("119990.00"), "PEN")
            ),
            new Vehicle(
                new VehicleBusinessId("V-SUV-002"),
                new VehicleDetails("Kia", "Sportage", "1.6T GT-Line AWD", "https://example.com/images/kia-sportage.jpg"),
                new TechnicalSpecs("1.6L Turbocharged 4-Cylinder", "7-Speed DCT", "177 HP", "AWD", "SUV"),
                new Money(new BigDecimal("109990.00"), "PEN")
            ),
            new Vehicle(
                new VehicleBusinessId("V-SUV-003"),
                new VehicleDetails("Hyundai", "Tucson", "2.5L SEL Preferred AWD", "https://example.com/images/hyundai-tucson.jpg"),
                new TechnicalSpecs("2.5L 4-Cylinder", "8-Speed Automatic", "187 HP", "AWD", "SUV"),
                new Money(new BigDecimal("99990.00"), "PEN")
            ),
            new Vehicle(
                new VehicleBusinessId("V-SED-001"),
                new VehicleDetails("Toyota", "Camry", "2.5L XSE V6 FWD", "https://example.com/images/toyota-camry.jpg"),
                new TechnicalSpecs("2.5L V6", "8-Speed Automatic", "203 HP", "FWD", "Sedan"),
                new Money(new BigDecimal("89990.00"), "PEN")
            ),
            new Vehicle(
                new VehicleBusinessId("V-SED-002"),
                new VehicleDetails("Honda", "Accord", "1.5T Sport FWD", "https://example.com/images/honda-accord.jpg"),
                new TechnicalSpecs("1.5L Turbocharged 4-Cylinder", "CVT Automatic", "192 HP", "FWD", "Sedan"),
                new Money(new BigDecimal("79990.00"), "PEN")
            )
        );

        vehicleRepository.saveAll(vehicles);
    }
}
