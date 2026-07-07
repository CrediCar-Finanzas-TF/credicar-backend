package com.credicar.backend.credit.domain.model.commands;

import java.math.BigDecimal;

public record GenerateQuotationCommand(
        Long clientId,
        Long vehicleId,
        BigDecimal financingAmount,
        BigDecimal initialFee,
        String currency,
        Integer totalQuotas,
        String modality,
        String interestRateType,
        Double interestRatePercentage,
        String capitalization,
        String gracePeriodType,
        Integer gracePeriodMonths,
        Double desgravamenRate,
        BigDecimal vehicularInsuranceMonthly,
        BigDecimal additionalExpenses,
        Double balloonPaymentPercentage,
        Double cokPercentage,
        Double roadsideAssistanceMonthly,
        Double extendedWarrantyMonthly,
        Double unemploymentInsuranceMonthly) {
}
