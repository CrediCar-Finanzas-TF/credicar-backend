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
                new VehicleDetails("Toyota", "RAV4", "2.5L Hybrid XSE AWD", "https://www.dubicars.com/images/3e2bbf/w_1300x760/laith-alobaidi-cars-trading-co/f48c288c-acae-4039-8696-f952f5db8ac0.jpeg"),
                new TechnicalSpecs("2.5L 4-Cylinder Hybrid", "CVT Automatic", "219 HP", "AWD", "SUV"),
                new Money(new BigDecimal("119990.00"), "PEN"),
                8, "Lima - Miraflores"
            ),
            new Vehicle(
                new VehicleBusinessId("V-SUV-002"),
                new VehicleDetails("Kia", "Sportage", "1.6T GT-Line AWD", "https://impx.hwntcdn.com/i/raw:1/aHR0cHM6Ly9jb21wb3Nlci5od250Y2RuLmNvbS8xMi91c2VkL2tpYS1zcG9ydGFnZS0xNi10LWdkaS0xMzhrd2gtZ3QtbGluZS1zLXN1di01ZHItcGV0cm9sLXBsdWctaW4taHlicmlkLWF1dG8tYXdkLWV1cm8tNi1zLXMtMjYxLWJocC00OTk2My10YXdyZmQydC5qcGc/kia-sportage-16-t-gdi-138kwh-gt-line-s-suv-5dr-petrol-plug-in-hybrid-auto-awd-euro-6-s-s-261-bhp-49963-tawrfd2t.jpg"),
                new TechnicalSpecs("1.6L Turbocharged 4-Cylinder", "7-Speed DCT", "177 HP", "AWD", "SUV"),
                new Money(new BigDecimal("109990.00"), "PEN"),
                5, "Lima - San Isidro"
            ),
            new Vehicle(
                new VehicleBusinessId("V-SUV-003"),
                new VehicleDetails("Hyundai", "Tucson", "2.5L SEL Preferred AWD", "https://vehicle-images.carscommerce.inc/c711-110009117/5NMJCCDE0TH647991/114d0bb57e7d1425e14e278aafd3bab6.jpg"),
                new TechnicalSpecs("2.5L 4-Cylinder", "8-Speed Automatic", "187 HP", "AWD", "SUV"),
                new Money(new BigDecimal("99990.00"), "PEN"),
                12, "Lima - Surco"
            ),
            new Vehicle(
                new VehicleBusinessId("V-SED-001"),
                new VehicleDetails("Toyota", "Camry", "2.5L XSE V6 FWD", "https://tap.fremontmotors.com/wp-content/uploads/2018/06/Camry-XSE-V6-small.jpg"),
                new TechnicalSpecs("2.5L V6", "8-Speed Automatic", "203 HP", "FWD", "Sedan"),
                new Money(new BigDecimal("89990.00"), "PEN"),
                3, "Arequipa - Centro"
            ),
            new Vehicle(
                new VehicleBusinessId("V-SED-002"),
                new VehicleDetails("Honda", "Accord", "1.5T Sport FWD", "https://content.homenetiol.com/2000292/2143540/0x0/df3279109cc34615a98510d716e9fb56.jpg"),
                new TechnicalSpecs("1.5L Turbocharged 4-Cylinder", "CVT Automatic", "192 HP", "FWD", "Sedan"),
                new Money(new BigDecimal("79990.00"), "PEN"),
                6, "Lima - La Molina"
            )
        );

        vehicleRepository.saveAll(vehicles);
    }
}
