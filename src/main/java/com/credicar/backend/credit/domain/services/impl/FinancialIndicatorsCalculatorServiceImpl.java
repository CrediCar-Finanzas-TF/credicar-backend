package com.credicar.backend.credit.domain.services.impl;

import com.credicar.backend.credit.domain.model.entities.PaymentScheduleItem;
import com.credicar.backend.credit.domain.model.valueobjects.FinancialIndicators;
import com.credicar.backend.credit.domain.services.FinancialIndicatorsCalculatorService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class FinancialIndicatorsCalculatorServiceImpl implements FinancialIndicatorsCalculatorService {

    @Override
    public FinancialIndicators calculate(BigDecimal financingAmount, List<PaymentScheduleItem> schedule,
                                          Double cokPercentage) {
        double loan = financingAmount.doubleValue();

        double cokMonthly = calculateMonthlyCok(cokPercentage);
        double van = calculateVAN(loan, schedule, cokMonthly);

        double tirMonthly = calculateTIR(loan, schedule);
        double tirAnnualPct = tirMonthly * 12 * 100;
        double tcea = (Math.pow(1 + tirMonthly, 12) - 1) * 100;

        return new FinancialIndicators(round(van, 2), round(tirAnnualPct, 4), round(tcea, 4));
    }

    private double calculateMonthlyCok(Double cokAnnualPercentage) {
        double cok = (cokAnnualPercentage != null ? cokAnnualPercentage : 0.0) / 100.0;
        return Math.pow(1 + cok, 1.0 / 12) - 1;
    }

    private double calculateVAN(double loan, List<PaymentScheduleItem> schedule, double discountRateMonthly) {
        double van = loan;
        for (int i = 0; i < schedule.size(); i++) {
            double cf = schedule.get(i).getMonthlyQuota().amount().doubleValue();
            van -= cf / Math.pow(1 + discountRateMonthly, i + 1);
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

    private double round(double value, int scale) {
        return BigDecimal.valueOf(value).setScale(scale, RoundingMode.HALF_UP).doubleValue();
    }
}
