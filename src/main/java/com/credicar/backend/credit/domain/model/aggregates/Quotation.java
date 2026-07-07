package com.credicar.backend.credit.domain.model.aggregates;

import com.credicar.backend.credit.domain.model.entities.PaymentScheduleItem;
import com.credicar.backend.credit.domain.model.valueobjects.FinancialIndicators;
import com.credicar.backend.credit.domain.model.valueobjects.GracePeriod;
import com.credicar.backend.credit.domain.model.valueobjects.Insurances;
import com.credicar.backend.credit.domain.model.valueobjects.InterestRate;
import com.credicar.backend.credit.domain.model.valueobjects.QuotationParameters;
import com.credicar.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Quotation extends AuditableAbstractAggregateRoot<Quotation> {

    @Column(nullable = false)
    private Long clientId;

    @Column(nullable = false)
    private Long vehicleId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "financingAmount.amount", column = @Column(name = "financingAmount")),
            @AttributeOverride(name = "financingAmount.currency", column = @Column(name = "financingCurrency")),
            @AttributeOverride(name = "initialFee.amount", column = @Column(name = "initialFeeAmount")),
            @AttributeOverride(name = "initialFee.currency", column = @Column(name = "initialFeeCurrency"))
    })
    private QuotationParameters parameters;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "type", column = @Column(name = "interestRateType")),
            @AttributeOverride(name = "percentage", column = @Column(name = "interestRatePercentage")),
            @AttributeOverride(name = "capitalization", column = @Column(name = "interestRateCapitalization"))
    })
    private InterestRate interestRate;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "type", column = @Column(name = "gracePeriodType")),
            @AttributeOverride(name = "months", column = @Column(name = "gracePeriodMonths"))
    })
    private GracePeriod gracePeriod;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "vehicularInsuranceMonthly.amount", column = @Column(name = "vehicularInsuranceMonthlyAmount")),
            @AttributeOverride(name = "vehicularInsuranceMonthly.currency", column = @Column(name = "vehicularInsuranceMonthlyCurrency")),
            @AttributeOverride(name = "roadsideAssistanceMonthly.amount", column = @Column(name = "roadsideAssistanceMonthlyAmount")),
            @AttributeOverride(name = "roadsideAssistanceMonthly.currency", column = @Column(name = "roadsideAssistanceMonthlyCurrency")),
            @AttributeOverride(name = "extendedWarrantyMonthly.amount", column = @Column(name = "extendedWarrantyMonthlyAmount")),
            @AttributeOverride(name = "extendedWarrantyMonthly.currency", column = @Column(name = "extendedWarrantyCurrency")),
            @AttributeOverride(name = "unemploymentInsuranceMonthly.amount", column = @Column(name = "unemploymentInsuranceMonthlyAmount")),
            @AttributeOverride(name = "unemploymentInsuranceMonthly.currency", column = @Column(name = "unemploymentInsuranceMonthlyCurrency"))
    })
    private Insurances insurances;

    @Embedded
    private FinancialIndicators financialIndicators;

    @OneToMany(mappedBy = "quotation", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OrderBy("quotaNumber ASC")
    private List<PaymentScheduleItem> schedule = new ArrayList<>();

    public Quotation(Long clientId, Long vehicleId, QuotationParameters parameters,
                     InterestRate interestRate, GracePeriod gracePeriod, Insurances insurances) {
        this.clientId = clientId;
        this.vehicleId = vehicleId;
        this.parameters = parameters;
        this.interestRate = interestRate;
        this.gracePeriod = gracePeriod;
        this.insurances = insurances;
        this.financialIndicators = new FinancialIndicators(0.0, 0.0, 0.0);
    }

    public void attachScheduleItems(List<PaymentScheduleItem> items) {
        items.forEach(item -> item.setQuotation(this));
        this.schedule.addAll(items);
    }

    public void attachFinancialIndicators(FinancialIndicators indicators) {
        this.financialIndicators = indicators;
    }
}
