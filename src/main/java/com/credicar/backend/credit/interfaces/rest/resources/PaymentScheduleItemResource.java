package com.credicar.backend.credit.interfaces.rest.resources;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PaymentScheduleItemResource(
        Integer quotaNumber,
        LocalDate dueDate,
        String graceType,
        BigDecimal initialBalance,
        BigDecimal amortization,
        BigDecimal interest,
        BigDecimal desgravamenInsurance,
        BigDecimal vehicularInsurance,
        BigDecimal roadsideAssistance,
        BigDecimal extendedWarranty,
        BigDecimal unemploymentInsurance,
        BigDecimal additionalExpenses,
        BigDecimal monthlyQuota,
        BigDecimal finalBalance,
        String currency) {
}
