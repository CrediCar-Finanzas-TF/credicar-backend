package com.credicar.backend.credit.domain.exceptions;

public class ClientNotAvailableException extends RuntimeException {

    public ClientNotAvailableException(Long clientId) {
        super("Client with ID '%d' was not found or is not available".formatted(clientId));
    }
}
