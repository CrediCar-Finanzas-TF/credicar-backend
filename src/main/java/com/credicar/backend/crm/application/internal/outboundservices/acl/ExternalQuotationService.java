package com.credicar.backend.crm.application.internal.outboundservices.acl;

public interface ExternalQuotationService {
    boolean existsQuotationsByClientId(Long clientId);
}
