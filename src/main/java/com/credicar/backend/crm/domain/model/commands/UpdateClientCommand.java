package com.credicar.backend.crm.domain.model.commands;

public record UpdateClientCommand(
        Long clientId,
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
