package com.credicar.backend.credit.domain.services.impl;

import com.credicar.backend.credit.domain.model.commands.GenerateQuotationCommand;
import com.credicar.backend.credit.domain.model.entities.PaymentScheduleItem;
import com.credicar.backend.credit.domain.services.PaymentScheduleCalculatorService;
import com.credicar.backend.shared.domain.model.valueobjects.Money;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentScheduleCalculatorServiceImpl implements PaymentScheduleCalculatorService {

    @Override
    public List<PaymentScheduleItem> calculate(GenerateQuotationCommand command) {
        double tem = calculateTEM(command.interestRateType(), command.interestRatePercentage(), command.capitalization());
        double loanAmount = command.financingAmount().doubleValue();
        String currency = command.currency();
        int n = command.totalQuotas();
        String graceType = command.gracePeriodType() != null ? command.gracePeriodType() : "NONE";
        int graceMonths = command.gracePeriodMonths() != null ? command.gracePeriodMonths() : 0;
        double desgravamenRate = (command.desgravamenRate() != null ? command.desgravamenRate() : 0.0) / 100.0;
        double vehicularIns = command.vehicularInsuranceMonthly() != null ? command.vehicularInsuranceMonthly().doubleValue() : 0.0;
        double roadsideIns = command.roadsideAssistanceMonthly() != null ? command.roadsideAssistanceMonthly() : 0.0;
        double extendedWarranty = command.extendedWarrantyMonthly() != null ? command.extendedWarrantyMonthly() : 0.0;
        double unemploymentIns = command.unemploymentInsuranceMonthly() != null ? command.unemploymentInsuranceMonthly() : 0.0;
        double additionalExpensesTotal = command.additionalExpenses() != null ? command.additionalExpenses().doubleValue() : 0.0;
        boolean isSmart = "SMART".equalsIgnoreCase(command.modality());
        double balloonPct = (isSmart && command.balloonPaymentPercentage() != null) ? command.balloonPaymentPercentage() : 0.30;
        double balloonAmount = isSmart ? loanAmount * balloonPct : 0.0;

        // Project balance through grace period to determine adjusted loan
        double postGraceBalance = loanAmount;
        if ("TOTAL".equalsIgnoreCase(graceType)) {
            for (int g = 0; g < graceMonths; g++) {
                postGraceBalance = postGraceBalance * (1 + tem);
            }
        }

        // Calculate base French-method quota for amortization periods
        int amortizationPeriods = n - graceMonths;
        double baseQuota = 0.0;
        if (amortizationPeriods > 0) {
            double balloonPV = balloonAmount > 0 ? balloonAmount / Math.pow(1 + tem, amortizationPeriods) : 0.0;
            double loanForAmortization = postGraceBalance - balloonPV;
            baseQuota = loanForAmortization * tem / (1 - Math.pow(1 + tem, -amortizationPeriods));
        }

        List<PaymentScheduleItem> items = new ArrayList<>();
        LocalDate firstDueDate = LocalDate.now().plusMonths(1);
        double balance = loanAmount;

        for (int i = 1; i <= n; i++) {
            LocalDate dueDate = firstDueDate.plusMonths(i - 1L);
            double initialBal = balance;
            double interestValue = balance * tem;
            double amortizationValue;
            double loanPart;
            String quotaGraceType;
            int periodInAmortization = i - graceMonths;
            boolean isLastPeriod = (periodInAmortization == amortizationPeriods);

            if ("TOTAL".equalsIgnoreCase(graceType) && i <= graceMonths) {
                quotaGraceType = "TOTAL";
                amortizationValue = 0.0;
                loanPart = 0.0;
                balance = balance + interestValue;

            } else if ("PARTIAL".equalsIgnoreCase(graceType) && i <= graceMonths) {
                quotaGraceType = "PARTIAL";
                amortizationValue = 0.0;
                loanPart = interestValue;

            } else {
                quotaGraceType = "NONE";
                if (isLastPeriod) {
                    // Pay exact remaining balance (for SMART, this converges to balloonAmount)
                    amortizationValue = balance;
                    loanPart = interestValue + amortizationValue;
                    balance = 0.0;
                } else {
                    amortizationValue = baseQuota - interestValue;
                    loanPart = baseQuota;
                    balance = balance - amortizationValue;
                }
            }

            double desgravamen = initialBal * desgravamenRate;
            double additional = (i == 1) ? additionalExpensesTotal : 0.0;
            double totalQuotaValue = loanPart + desgravamen + vehicularIns + roadsideIns + extendedWarranty + unemploymentIns + additional;

            items.add(new PaymentScheduleItem(
                    i, dueDate, quotaGraceType,
                    money(initialBal, currency),
                    money(amortizationValue, currency),
                    money(interestValue, currency),
                    money(desgravamen, currency),
                    money(vehicularIns, currency),
                    money(roadsideIns, currency),
                    money(extendedWarranty, currency),
                    money(unemploymentIns, currency),
                    money(additional, currency),
                    money(totalQuotaValue, currency),
                    money(balance, currency)
            ));
        }

        return items;
    }

    private double calculateTEM(String type, Double percentage, String capitalization) {
        double rate = percentage / 100.0;
        return switch (type.toUpperCase()) {
            case "EFFECTIVE" -> Math.pow(1 + rate, 1.0 / 12) - 1;
            case "NOMINAL" -> {
                int m = resolveCapitalizationPeriods(capitalization);
                yield Math.pow(1 + rate / m, m / 12.0) - 1;
            }
            default -> throw new IllegalArgumentException("Unsupported interest rate type: " + type);
        };
    }

    private int resolveCapitalizationPeriods(String capitalization) {
        if (capitalization == null || capitalization.isBlank()) return 12;
        return switch (capitalization.toUpperCase()) {
            case "DAILY" -> 360;
            case "MONTHLY" -> 12;
            case "QUARTERLY" -> 4;
            case "SEMI-ANNUALLY", "SEMI_ANNUALLY" -> 2;
            case "ANNUALLY" -> 1;
            default -> 12;
        };
    }

    private Money money(double value, String currency) {
        BigDecimal rounded = BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP);
        return new Money(rounded.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : rounded, currency);
    }
}
