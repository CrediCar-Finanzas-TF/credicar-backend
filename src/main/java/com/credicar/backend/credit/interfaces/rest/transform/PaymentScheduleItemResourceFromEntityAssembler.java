package com.credicar.backend.credit.interfaces.rest.transform;

import com.credicar.backend.credit.domain.model.entities.PaymentScheduleItem;
import com.credicar.backend.credit.interfaces.rest.resources.PaymentScheduleItemResource;

public class PaymentScheduleItemResourceFromEntityAssembler {

    public static PaymentScheduleItemResource toResourceFromEntity(PaymentScheduleItem item) {
        return new PaymentScheduleItemResource(
                item.getQuotaNumber(),
                item.getDueDate(),
                item.getGraceType(),
                item.getInitialBalance().amount(),
                item.getAmortization().amount(),
                item.getInterest().amount(),
                item.getDesgravamenInsurance().amount(),
                item.getVehicularInsurance().amount(),
                item.getRoadsideAssistance().amount(),
                item.getExtendedWarranty().amount(),
                item.getUnemploymentInsurance().amount(),
                item.getAdditionalExpenses().amount(),
                item.getMonthlyQuota().amount(),
                item.getFinalBalance().amount(),
                item.getMonthlyQuota().currency()
        );
    }
}
