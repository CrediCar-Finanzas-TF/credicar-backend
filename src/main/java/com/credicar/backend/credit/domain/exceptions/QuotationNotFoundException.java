package com.credicar.backend.credit.domain.exceptions;

public class QuotationNotFoundException extends RuntimeException {

    public QuotationNotFoundException(Long quotationId) {
        super("Quotation with ID '%d' was not found".formatted(quotationId));
    }
}
