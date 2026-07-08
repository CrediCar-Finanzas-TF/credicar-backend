package com.credicar.backend.credit.interfaces.rest.transform;

import com.credicar.backend.credit.domain.model.commands.GenerateQuotationCommand;
import com.credicar.backend.credit.interfaces.rest.resources.GenerateQuotationResource;

public class GenerateQuotationCommandFromResourceAssembler {

    public static GenerateQuotationCommand toCommandFromResource(GenerateQuotationResource resource) {
        return new GenerateQuotationCommand(
                resource.clientId(),
                resource.vehicleId(),
                resource.financingAmount(),
                resource.initialFee(),
                resource.currency(),
                resource.totalQuotas(),
                resource.modality(),
                resource.interestRateType(),
                resource.interestRatePercentage(),
                resource.capitalization(),
                resource.gracePeriodType(),
                resource.gracePeriodMonths(),
                resource.desgravamenRate(),
                resource.vehicularInsuranceMonthly(),
                resource.additionalExpenses(),
                resource.balloonPaymentPercentage(),
                resource.cokPercentage(),
                resource.roadsideAssistanceMonthly(),
                resource.extendedWarrantyMonthly(),
                resource.unemploymentInsuranceMonthly(),
                resource.notaryFee(),
                resource.registryFee()
        );
    }
}
