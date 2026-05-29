package com.credicar.backend.crm.domain.exceptions;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(Long clientId) {
        super("Client not found with id: " + clientId);
    }
}
