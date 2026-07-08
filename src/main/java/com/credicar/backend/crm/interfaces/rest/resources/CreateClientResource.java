package com.credicar.backend.crm.interfaces.rest.resources;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateClientResource(
        @NotBlank(message = "Document type is required")
        String documentType,

        @NotBlank(message = "Document number is required")
        String documentNumber,

        @NotBlank(message = "First name is required")
        String firstName,

        @NotBlank(message = "Last name is required")
        String lastName,

        @NotBlank(message = "Phone is required")
        String phone,

        @NotBlank(message = "Email is required")
        @Email(message = "Email must be a valid email address")
        String email,

        @NotBlank(message = "Street address is required")
        String streetAddress,

        @NotBlank(message = "Company is required")
        String company,

        @NotNull(message = "Monthly income is required")
        @Positive(message = "Monthly income must be a positive number")
        Double monthlyIncome,

        @NotNull(message = "Seniority years is required")
        @Positive(message = "Seniority years must be a positive number")
        Integer seniorityYears
) {
}
