package com.credicar.backend.crm.interfaces.acl;

import com.credicar.backend.crm.domain.model.queries.GetClientByDocumentNumberQuery;
import com.credicar.backend.crm.domain.services.ClientQueryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CrmContextFacadeImpl implements CrmContextFacade {

    private final ClientQueryService clientQueryService;

    public CrmContextFacadeImpl(ClientQueryService clientQueryService) {
        this.clientQueryService = clientQueryService;
    }

    @Override
    public Optional<Long> fetchClientIdByDocumentNumber(String documentNumber) {
        return clientQueryService.handle(new GetClientByDocumentNumberQuery(documentNumber))
                .map(client -> client.getId());
    }
}
