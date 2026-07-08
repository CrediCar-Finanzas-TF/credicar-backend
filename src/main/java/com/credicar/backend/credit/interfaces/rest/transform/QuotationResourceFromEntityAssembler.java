package com.credicar.backend.credit.interfaces.rest.transform;

import com.credicar.backend.credit.domain.model.aggregates.Quotation;
import com.credicar.backend.credit.interfaces.rest.resources.QuotationResource;

import java.util.List;

public class QuotationResourceFromEntityAssembler {

    public static QuotationResource toResourceFromEntity(Quotation quotation) {
        var schedule = quotation.getSchedule().stream()
                .map(PaymentScheduleItemResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return new QuotationResource(
                quotation.getId(),
                quotation.getClientId(),
                quotation.getVehicleId(),
                quotation.getParameters().financingAmount().amount(),
                quotation.getParameters().initialFee().amount(),
                quotation.getParameters().financingAmount().currency(),
                quotation.getParameters().totalQuotas(),
                quotation.getParameters().modality(),
                quotation.getInterestRate().type(),
                quotation.getInterestRate().percentage(),
                quotation.getInterestRate().capitalization(),
                quotation.getGracePeriod().type(),
                quotation.getGracePeriod().months(),
                quotation.getInsurances().desgravamenRate(),
                quotation.getInsurances().vehicularInsuranceMonthly().amount(),
                quotation.getInsurances().roadsideAssistanceMonthly().amount(),
                quotation.getInsurances().extendedWarrantyMonthly().amount(),
                quotation.getInsurances().unemploymentInsuranceMonthly().amount(),
                quotation.getNotaryFee(),
                quotation.getRegistryFee(),
                quotation.getCokPercentage(),
                quotation.getFinancialIndicators().van(),
                quotation.getFinancialIndicators().tir(),
                quotation.getFinancialIndicators().tcea(),
                schedule
        );
    }
}
