package com.credicar.backend.crm.domain.model.queries;

public record GetClientsBySearchQuery(String searchTerm, int page, int size) {
}
