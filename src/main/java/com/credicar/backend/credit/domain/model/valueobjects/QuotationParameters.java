package com.credicar.backend.credit.domain.model.valueobjects;

import com.credicar.backend.shared.domain.model.valueobjects.Money;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public record QuotationParameters(
        @Embedded Money financingAmount,
        @Embedded Money initialFee,
        Integer totalQuotas,
        String modality) {
}
