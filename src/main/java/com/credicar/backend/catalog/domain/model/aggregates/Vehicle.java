package com.credicar.backend.catalog.domain.model.aggregates;

import com.credicar.backend.catalog.domain.model.valueobjects.TechnicalSpecs;
import com.credicar.backend.catalog.domain.model.valueobjects.VehicleBusinessId;
import com.credicar.backend.catalog.domain.model.valueobjects.VehicleDetails;
import com.credicar.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.credicar.backend.shared.domain.model.valueobjects.Money;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Vehicle extends AuditableAbstractAggregateRoot<Vehicle> {

    @Embedded
    private VehicleBusinessId vehicleBusinessId;

    @Embedded
    private VehicleDetails vehicleDetails;

    @Embedded
    private TechnicalSpecs technicalSpecs;

    @Embedded
    private Money price;

    public Vehicle(VehicleBusinessId vehicleBusinessId, VehicleDetails vehicleDetails,
                   TechnicalSpecs technicalSpecs, Money price) {
        this.vehicleBusinessId = vehicleBusinessId;
        this.vehicleDetails = vehicleDetails;
        this.technicalSpecs = technicalSpecs;
        this.price = price;
    }
}
