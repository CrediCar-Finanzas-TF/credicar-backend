package com.credicar.backend.crm.application.internal.queryservices;

import com.credicar.backend.crm.domain.model.aggregates.Client;
import com.credicar.backend.crm.domain.model.queries.GetClientByDocumentNumberQuery;
import com.credicar.backend.crm.domain.model.queries.GetClientByIdQuery;
import com.credicar.backend.crm.domain.services.ClientQueryService;
import com.credicar.backend.crm.infrastructure.persistence.jpa.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientQueryServiceImpl implements ClientQueryService {

    private final ClientRepository clientRepository;

    public ClientQueryServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Optional<Client> handle(GetClientByIdQuery query) {
        return clientRepository.findById(query.clientId());
    }

    @Override
    public Optional<Client> handle(GetClientByDocumentNumberQuery query) {
        return clientRepository.findByDocumentIdDocumentNumber(query.documentNumber());
    }
}
