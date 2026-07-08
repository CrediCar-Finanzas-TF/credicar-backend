package com.credicar.backend.credit.application.internal.outboundservices.acl;

public interface ExternalClientService {
    boolean existsClientById(Long clientId);
}
