package com.credicar.backend.crm.domain.exceptions;

public class ClientAlreadyExistsException extends RuntimeException {
    public ClientAlreadyExistsException(String documentType, String documentNumber) {
        super("Client already exists with document: " + documentType + " " + documentNumber);
    }
}
