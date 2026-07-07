package com.credicar.backend.credit.interfaces.rest.resources;

import java.math.BigDecimal;
import java.util.List;

public record QuotationResource(
        Long id,
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
        BigDecimal roadsideAssistanceMonthly,
        BigDecimal extendedWarrantyMonthly,
        BigDecimal unemploymentInsuranceMonthly,
        Double van,
        Double tir,
        Double tcea,
        List<PaymentScheduleItemResource> schedule) {
}
