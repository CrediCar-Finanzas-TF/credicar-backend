package com.credicar.backend.shared.interfaces.rest;

import com.credicar.backend.catalog.domain.exceptions.VehicleNotFoundException;
import com.credicar.backend.credit.domain.exceptions.ClientNotAvailableException;
import com.credicar.backend.credit.domain.exceptions.QuotationNotFoundException;
import com.credicar.backend.credit.domain.exceptions.VehicleNotAvailableException;
import com.credicar.backend.crm.domain.exceptions.ClientAlreadyExistsException;
import com.credicar.backend.crm.domain.exceptions.ClientHasQuotationsException;
import com.credicar.backend.crm.domain.exceptions.ClientNotFoundException;
import com.credicar.backend.iam.domain.exceptions.UserAlreadyExistsException;
import com.credicar.backend.iam.domain.exceptions.UserNotFoundException;
import com.credicar.backend.shared.interfaces.rest.resources.MessageResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            ClientNotFoundException.class,
            QuotationNotFoundException.class,
            VehicleNotFoundException.class
    })
    public ResponseEntity<MessageResource> handleNotFound(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResource(exception.getMessage()));
    }

    @ExceptionHandler(ClientHasQuotationsException.class)
    public ResponseEntity<MessageResource> handleConflict(ClientHasQuotationsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResource(exception.getMessage()));
    }

    @ExceptionHandler({
            ClientAlreadyExistsException.class,
            UserAlreadyExistsException.class,
            ClientNotAvailableException.class,
            VehicleNotAvailableException.class
    })
    public ResponseEntity<MessageResource> handleBadRequest(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResource(exception.getMessage()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<MessageResource> handleUnauthorized(UserNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResource("Invalid email or password"));
    }
}
