package com.credicar.backend.crm.domain.services;

import com.credicar.backend.crm.domain.model.aggregates.Client;
import com.credicar.backend.crm.domain.model.commands.CreateClientCommand;
import com.credicar.backend.crm.domain.model.commands.DeleteClientCommand;
import com.credicar.backend.crm.domain.model.commands.UpdateClientCommand;

import java.util.Optional;

public interface ClientCommandService {
    Optional<Client> handle(CreateClientCommand command);
    Optional<Client> handle(UpdateClientCommand command);
    void handle(DeleteClientCommand command);
}
