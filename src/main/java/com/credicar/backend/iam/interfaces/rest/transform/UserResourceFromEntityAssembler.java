package com.credicar.backend.iam.interfaces.rest.transform;

import com.credicar.backend.iam.domain.model.aggregates.User;
import com.credicar.backend.iam.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {

    public static UserResource toResourceFromEntity(User entity) {
        return new UserResource(entity.getId(), entity.getFullName(), entity.getEmail());
    }
}
