package com.credicar.backend.iam.interfaces.rest.transform;

import com.credicar.backend.iam.domain.model.aggregates.User;
import com.credicar.backend.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {

    public static AuthenticatedUserResource toResourceFromEntity(User entity, String token) {
        return new AuthenticatedUserResource(
                entity.getId(),
                entity.getFullName(),
                entity.getEmail(),
                token);
    }
}
