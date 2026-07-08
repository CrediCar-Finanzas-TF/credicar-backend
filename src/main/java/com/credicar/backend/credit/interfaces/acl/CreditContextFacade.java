package com.credicar.backend.credit.interfaces.acl;

/**
 * Anti-Corruption Layer facade for the Credit bounded context.
 * Other bounded contexts must use this interface to query quotation data.
 */
public interface CreditContextFacade {
    boolean existsQuotationsByClientId(Long clientId);
}
