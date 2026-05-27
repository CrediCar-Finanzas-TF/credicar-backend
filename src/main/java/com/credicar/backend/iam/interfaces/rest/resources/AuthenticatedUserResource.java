package com.credicar.backend.iam.interfaces.rest.resources;

public record AuthenticatedUserResource(Long id, String fullName, String email, String token) {
}
