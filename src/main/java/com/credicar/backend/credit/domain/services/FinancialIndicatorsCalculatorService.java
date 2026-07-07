package com.credicar.backend.credit.domain.services;

import com.credicar.backend.credit.domain.model.entities.PaymentScheduleItem;
import com.credicar.backend.credit.domain.model.valueobjects.FinancialIndicators;

import java.math.BigDecimal;
import java.util.List;

public interface FinancialIndicatorsCalculatorService {
    FinancialIndicators calculate(BigDecimal financingAmount, List<PaymentScheduleItem> schedule,
                                  Double cokPercentage);
}
