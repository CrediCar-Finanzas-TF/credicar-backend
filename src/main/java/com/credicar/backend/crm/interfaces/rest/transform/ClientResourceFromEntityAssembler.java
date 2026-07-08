package com.credicar.backend.crm.interfaces.rest.transform;

import com.credicar.backend.crm.domain.model.aggregates.Client;
import com.credicar.backend.crm.interfaces.rest.resources.ClientResource;

public class ClientResourceFromEntityAssembler {

    public static ClientResource toResourceFromEntity(Client client) {
        return new ClientResource(
                client.getId(),
                client.getDocumentId().documentType(),
                client.getDocumentId().documentNumber(),
                client.getPersonName().firstName(),
                client.getPersonName().lastName(),
                client.getContactInfo().phone(),
                client.getContactInfo().email(),
                client.getAddress().streetAddress(),
                client.getEmploymentProfile().company(),
                client.getEmploymentProfile().monthlyIncome(),
                client.getEmploymentProfile().seniorityYears()
        );
    }
}
