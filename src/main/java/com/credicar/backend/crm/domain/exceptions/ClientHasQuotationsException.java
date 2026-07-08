package com.credicar.backend.crm.domain.exceptions;

public class ClientHasQuotationsException extends RuntimeException {
    public ClientHasQuotationsException(Long clientId) {
        super("Client with ID '%d' has associated quotations and cannot be deleted".formatted(clientId));
    }
}
