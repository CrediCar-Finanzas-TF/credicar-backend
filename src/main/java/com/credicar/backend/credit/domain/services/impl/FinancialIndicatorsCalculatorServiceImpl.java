package com.credicar.backend.credit.domain.services.impl;

import com.credicar.backend.credit.domain.model.entities.PaymentScheduleItem;
import com.credicar.backend.credit.domain.model.valueobjects.FinancialIndicators;
import com.credicar.backend.credit.domain.model.valueobjects.InterestRate;
import com.credicar.backend.credit.domain.services.FinancialIndicatorsCalculatorService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class FinancialIndicatorsCalculatorServiceImpl implements FinancialIndicatorsCalculatorService {

    @Override
    public FinancialIndicators calculate(BigDecimal financingAmount, List<PaymentScheduleItem> schedule, InterestRate interestRate) {
        double tem = calculateTEM(interestRate.type(), interestRate.percentage(), interestRate.capitalization());
        double loan = financingAmount.doubleValue();

        double van = calculateVAN(loan, schedule, tem);
        double tirMonthly = calculateTIR(loan, schedule);
        double tcea = (Math.pow(1 + tirMonthly, 12) - 1) * 100;
        double tirAnnualPct = tirMonthly * 12 * 100;

        return new FinancialIndicators(
                round(van, 2),
                round(tirAnnualPct, 4),
                round(tcea, 4)
        );
    }

    private double calculateVAN(double loan, List<PaymentScheduleItem> schedule, double tem) {
        double van = -loan;
        for (int i = 0; i < schedule.size(); i++) {
            double cf = schedule.get(i).getMonthlyQuota().amount().doubleValue();
            van += cf / Math.pow(1 + tem, i + 1);
        }
        return van;
    }

    private double calculateTIR(double loan, List<PaymentScheduleItem> schedule) {
        double r = 0.01;
        for (int iter = 0; iter < 1000; iter++) {
            double f = -loan;
            double df = 0.0;
            for (int i = 0; i < schedule.size(); i++) {
                double cf = schedule.get(i).getMonthlyQuota().amount().doubleValue();
                double t = i + 1;
                double discounted = Math.pow(1 + r, t);
                f += cf / discounted;
                df -= t * cf / (discounted * (1 + r));
            }
            if (Math.abs(df) < 1e-12) break;
            double rNew = r - f / df;
            if (Math.abs(rNew - r) < 1e-10) {
                r = rNew;
                break;
            }
            r = rNew;
        }
        return r;
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

    private double round(double value, int scale) {
        return BigDecimal.valueOf(value).setScale(scale, RoundingMode.HALF_UP).doubleValue();
    }
}
