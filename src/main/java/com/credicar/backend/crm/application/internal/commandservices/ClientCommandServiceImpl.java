package com.credicar.backend.crm.application.internal.commandservices;

import com.credicar.backend.crm.domain.exceptions.ClientAlreadyExistsException;
import com.credicar.backend.crm.domain.exceptions.ClientNotFoundException;
import com.credicar.backend.crm.domain.model.aggregates.Client;
import com.credicar.backend.crm.domain.model.commands.CreateClientCommand;
import com.credicar.backend.crm.domain.model.commands.UpdateClientCommand;
import com.credicar.backend.crm.domain.model.valueobjects.DocumentId;
import com.credicar.backend.crm.domain.services.ClientCommandService;
import com.credicar.backend.crm.infrastructure.persistence.jpa.repositories.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientCommandServiceImpl implements ClientCommandService {

    private final ClientRepository clientRepository;

    public ClientCommandServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    @Transactional
    public Optional<Client> handle(CreateClientCommand command) {
        var documentId = new DocumentId(command.documentType(), command.documentNumber());
        if (clientRepository.existsByDocumentId(documentId)) {
            throw new ClientAlreadyExistsException(command.documentType(), command.documentNumber());
        }
        var client = new Client(command);
        clientRepository.save(client);
        return Optional.of(client);
    }

    @Override
    @Transactional
    public Optional<Client> handle(UpdateClientCommand command) {
        var client = clientRepository.findById(command.clientId())
                .orElseThrow(() -> new ClientNotFoundException(command.clientId()));
        client.updateInformation(command);
        clientRepository.save(client);
        return Optional.of(client);
    }
}
