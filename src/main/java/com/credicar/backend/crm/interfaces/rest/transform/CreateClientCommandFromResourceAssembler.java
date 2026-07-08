package com.credicar.backend.crm.interfaces.rest.transform;

import com.credicar.backend.crm.domain.model.commands.CreateClientCommand;
import com.credicar.backend.crm.interfaces.rest.resources.CreateClientResource;

public class CreateClientCommandFromResourceAssembler {

    public static CreateClientCommand toCommandFromResource(CreateClientResource resource) {
        return new CreateClientCommand(
                resource.documentType(),
                resource.documentNumber(),
                resource.firstName(),
                resource.lastName(),
                resource.phone(),
                resource.email(),
                resource.streetAddress(),
                resource.company(),
                resource.monthlyIncome(),
                resource.seniorityYears()
        );
    }
}
