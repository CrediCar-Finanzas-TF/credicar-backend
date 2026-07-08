package com.credicar.backend.crm.application.internal.outboundservices.acl;

import com.credicar.backend.credit.interfaces.acl.CreditContextFacade;
import org.springframework.stereotype.Service;

@Service
public class ExternalQuotationServiceImpl implements ExternalQuotationService {

    private final CreditContextFacade creditContextFacade;

    public ExternalQuotationServiceImpl(CreditContextFacade creditContextFacade) {
        this.creditContextFacade = creditContextFacade;
    }

    @Override
    public boolean existsQuotationsByClientId(Long clientId) {
        return creditContextFacade.existsQuotationsByClientId(clientId);
    }
}
