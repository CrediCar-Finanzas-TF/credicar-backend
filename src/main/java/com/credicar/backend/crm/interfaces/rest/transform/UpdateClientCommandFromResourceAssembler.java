package com.credicar.backend.crm.interfaces.rest.transform;

import com.credicar.backend.crm.domain.model.commands.UpdateClientCommand;
import com.credicar.backend.crm.interfaces.rest.resources.UpdateClientResource;

public class UpdateClientCommandFromResourceAssembler {

    public static UpdateClientCommand toCommandFromResource(Long clientId, UpdateClientResource resource) {
        return new UpdateClientCommand(
                clientId,
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
