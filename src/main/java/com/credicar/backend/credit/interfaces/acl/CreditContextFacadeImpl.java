package com.credicar.backend.credit.interfaces.acl;

import com.credicar.backend.credit.infrastructure.persistence.jpa.repositories.QuotationRepository;
import org.springframework.stereotype.Service;

@Service
public class CreditContextFacadeImpl implements CreditContextFacade {

    private final QuotationRepository quotationRepository;

    public CreditContextFacadeImpl(QuotationRepository quotationRepository) {
        this.quotationRepository = quotationRepository;
    }

    @Override
    public boolean existsQuotationsByClientId(Long clientId) {
        return quotationRepository.existsByClientId(clientId);
    }
}
