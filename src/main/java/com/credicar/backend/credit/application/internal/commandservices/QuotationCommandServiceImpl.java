package com.credicar.backend.credit.application.internal.commandservices;

import com.credicar.backend.credit.application.internal.outboundservices.acl.ExternalClientService;
import com.credicar.backend.credit.application.internal.outboundservices.acl.ExternalVehicleService;
import com.credicar.backend.credit.domain.exceptions.ClientNotAvailableException;
import com.credicar.backend.credit.domain.exceptions.VehicleNotAvailableException;
import com.credicar.backend.credit.domain.model.aggregates.Quotation;
import com.credicar.backend.credit.domain.model.commands.GenerateQuotationCommand;
import com.credicar.backend.credit.domain.model.valueobjects.FinancialIndicators;
import com.credicar.backend.credit.domain.model.valueobjects.GracePeriod;
import com.credicar.backend.credit.domain.model.valueobjects.Insurances;
import com.credicar.backend.credit.domain.model.valueobjects.InterestRate;
import com.credicar.backend.credit.domain.model.valueobjects.QuotationParameters;
import com.credicar.backend.credit.domain.services.FinancialIndicatorsCalculatorService;
import com.credicar.backend.credit.domain.services.PaymentScheduleCalculatorService;
import com.credicar.backend.credit.domain.services.QuotationCommandService;
import com.credicar.backend.credit.infrastructure.persistence.jpa.repositories.QuotationRepository;
import com.credicar.backend.shared.domain.model.valueobjects.Money;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QuotationCommandServiceImpl implements QuotationCommandService {

    private final QuotationRepository quotationRepository;
    private final ExternalClientService externalClientService;
    private final ExternalVehicleService externalVehicleService;
    private final PaymentScheduleCalculatorService scheduleCalculator;
    private final FinancialIndicatorsCalculatorService indicatorsCalculator;

    public QuotationCommandServiceImpl(QuotationRepository quotationRepository,
                                       ExternalClientService externalClientService,
                                       ExternalVehicleService externalVehicleService,
                                       PaymentScheduleCalculatorService scheduleCalculator,
                                       FinancialIndicatorsCalculatorService indicatorsCalculator) {
        this.quotationRepository = quotationRepository;
        this.externalClientService = externalClientService;
        this.externalVehicleService = externalVehicleService;
        this.scheduleCalculator = scheduleCalculator;
        this.indicatorsCalculator = indicatorsCalculator;
    }

    @Override
    public Quotation handle(GenerateQuotationCommand command) {
        if (!externalClientService.existsClientById(command.clientId()))
            throw new ClientNotAvailableException(command.clientId());

        if (!externalVehicleService.existsVehicleById(command.vehicleId()))
            throw new VehicleNotAvailableException(command.vehicleId());

        var parameters = new QuotationParameters(
                new Money(command.financingAmount(), command.currency()),
                new Money(command.initialFee(), command.currency()),
                command.totalQuotas(),
                command.modality()
        );
        var interestRate = new InterestRate(
                command.interestRateType(),
                command.interestRatePercentage(),
                command.capitalization()
        );
        var gracePeriod = new GracePeriod(
                command.gracePeriodType() != null ? command.gracePeriodType() : "NONE",
                command.gracePeriodMonths() != null ? command.gracePeriodMonths() : 0
        );
        var insurances = new Insurances(
                command.desgravamenRate() != null ? command.desgravamenRate() : 0.0,
                new Money(command.vehicularInsuranceMonthly(), command.currency())
        );

        var quotation = new Quotation(command.clientId(), command.vehicleId(),
                parameters, interestRate, gracePeriod, insurances);

        var schedule = scheduleCalculator.calculate(command);
        FinancialIndicators indicators = indicatorsCalculator.calculate(
                command.financingAmount(), schedule, interestRate);

        quotation.attachScheduleItems(schedule);
        quotation.attachFinancialIndicators(indicators);

        return quotationRepository.save(quotation);
    }
}
