package com.credicar.backend.crm.interfaces.rest.resources;

public record ClientResource(
        Long id,
        String documentType,
        String documentNumber,
        String firstName,
        String lastName,
        String phone,
        String email,
        String streetAddress,
        String company,
        Double monthlyIncome,
        Integer seniorityYears
) {
}
