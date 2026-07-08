package com.credicar.backend.crm.interfaces.acl;

import java.util.Optional;

/**
 * Anti-Corruption Layer facade for the CRM bounded context.
 * Other bounded contexts must use this interface to query client identity data.
 */
public interface CrmContextFacade {
    Optional<Long> fetchClientIdByDocumentNumber(String documentNumber);
    boolean existsClientById(Long clientId);
}
