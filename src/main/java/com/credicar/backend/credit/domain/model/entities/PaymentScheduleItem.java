package com.credicar.backend.credit.domain.model.entities;

import com.credicar.backend.credit.domain.model.aggregates.Quotation;
import com.credicar.backend.shared.domain.model.entities.AuditableModel;
import com.credicar.backend.shared.domain.model.valueobjects.Money;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class PaymentScheduleItem extends AuditableModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quotationId", nullable = false)
    @Setter
    private Quotation quotation;

    @Column(nullable = false)
    private Integer quotaNumber;

    @Column(nullable = false)
    private LocalDate dueDate;

    @Column(nullable = false)
    private String graceType;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "initialBalanceAmount")),
            @AttributeOverride(name = "currency", column = @Column(name = "initialBalanceCurrency"))
    })
    private Money initialBalance;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "amortizationAmount")),
            @AttributeOverride(name = "currency", column = @Column(name = "amortizationCurrency"))
    })
    private Money amortization;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "interestAmount")),
            @AttributeOverride(name = "currency", column = @Column(name = "interestCurrency"))
    })
    private Money interest;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "desgravamenInsuranceAmount")),
            @AttributeOverride(name = "currency", column = @Column(name = "desgravamenInsuranceCurrency"))
    })
    private Money desgravamenInsurance;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "vehicularInsuranceAmount")),
            @AttributeOverride(name = "currency", column = @Column(name = "vehicularInsuranceCurrency"))
    })
    private Money vehicularInsurance;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "roadsideAssistanceAmount")),
            @AttributeOverride(name = "currency", column = @Column(name = "roadsideAssistanceCurrency"))
    })
    private Money roadsideAssistance;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "extendedWarrantyAmount")),
            @AttributeOverride(name = "currency", column = @Column(name = "extendedWarrantyCurrency"))
    })
    private Money extendedWarranty;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "unemploymentInsuranceAmount")),
            @AttributeOverride(name = "currency", column = @Column(name = "unemploymentInsuranceCurrency"))
    })
    private Money unemploymentInsurance;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "additionalExpensesAmount")),
            @AttributeOverride(name = "currency", column = @Column(name = "additionalExpensesCurrency"))
    })
    private Money additionalExpenses;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "monthlyQuotaAmount")),
            @AttributeOverride(name = "currency", column = @Column(name = "monthlyQuotaCurrency"))
    })
    private Money monthlyQuota;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "finalBalanceAmount")),
            @AttributeOverride(name = "currency", column = @Column(name = "finalBalanceCurrency"))
    })
    private Money finalBalance;

    public PaymentScheduleItem(Integer quotaNumber, LocalDate dueDate, String graceType,
                                Money initialBalance, Money amortization, Money interest,
                                Money desgravamenInsurance, Money vehicularInsurance,
                                Money roadsideAssistance, Money extendedWarranty, Money unemploymentInsurance,
                                Money additionalExpenses, Money monthlyQuota, Money finalBalance) {
        this.quotaNumber = quotaNumber;
        this.dueDate = dueDate;
        this.graceType = graceType;
        this.initialBalance = initialBalance;
        this.amortization = amortization;
        this.interest = interest;
        this.desgravamenInsurance = desgravamenInsurance;
        this.vehicularInsurance = vehicularInsurance;
        this.roadsideAssistance = roadsideAssistance;
        this.extendedWarranty = extendedWarranty;
        this.unemploymentInsurance = unemploymentInsurance;
        this.additionalExpenses = additionalExpenses;
        this.monthlyQuota = monthlyQuota;
        this.finalBalance = finalBalance;
    }
}
