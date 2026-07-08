package com.credicar.backend.crm.domain.services;

import com.credicar.backend.crm.domain.model.aggregates.Client;
import com.credicar.backend.crm.domain.model.queries.GetClientByDocumentNumberQuery;
import com.credicar.backend.crm.domain.model.queries.GetClientByIdQuery;
import com.credicar.backend.crm.domain.model.queries.GetClientsBySearchQuery;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ClientQueryService {
    Optional<Client> handle(GetClientByIdQuery query);
    Optional<Client> handle(GetClientByDocumentNumberQuery query);
    Page<Client> handle(GetClientsBySearchQuery query);
}
