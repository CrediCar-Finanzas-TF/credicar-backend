package com.credicar.backend.credit.domain.services;

import com.credicar.backend.credit.domain.model.aggregates.Quotation;
import com.credicar.backend.credit.domain.model.commands.GenerateQuotationCommand;

public interface QuotationCommandService {
    Quotation handle(GenerateQuotationCommand command);
    Quotation preview(GenerateQuotationCommand command);
}
