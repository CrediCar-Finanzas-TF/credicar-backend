package com.credicar.backend.crm.domain.model.commands;

public record CreateClientCommand(
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
