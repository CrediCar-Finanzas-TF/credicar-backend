package com.credicar.backend.credit.application.internal.outboundservices.acl;

import com.credicar.backend.crm.interfaces.acl.CrmContextFacade;
import org.springframework.stereotype.Service;

@Service
public class ExternalClientServiceImpl implements ExternalClientService {

    private final CrmContextFacade crmContextFacade;

    public ExternalClientServiceImpl(CrmContextFacade crmContextFacade) {
        this.crmContextFacade = crmContextFacade;
    }

    @Override
    public boolean existsClientById(Long clientId) {
        return crmContextFacade.existsClientById(clientId);
    }
}
