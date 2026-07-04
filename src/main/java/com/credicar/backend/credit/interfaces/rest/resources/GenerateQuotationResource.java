package com.credicar.backend.credit.interfaces.rest.resources;

import java.math.BigDecimal;

public record GenerateQuotationResource(
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
        Double balloonPaymentPercentage) {
}
