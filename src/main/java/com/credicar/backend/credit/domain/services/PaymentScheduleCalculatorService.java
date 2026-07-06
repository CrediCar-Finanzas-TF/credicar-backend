package com.credicar.backend.credit.domain.services;

import com.credicar.backend.credit.domain.model.commands.GenerateQuotationCommand;
import com.credicar.backend.credit.domain.model.entities.PaymentScheduleItem;

import java.util.List;

public interface PaymentScheduleCalculatorService {
    List<PaymentScheduleItem> calculate(GenerateQuotationCommand command);
}
