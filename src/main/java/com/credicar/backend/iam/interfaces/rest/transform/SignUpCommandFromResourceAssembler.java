package com.credicar.backend.iam.interfaces.rest.transform;

import com.credicar.backend.iam.domain.model.commands.SignUpCommand;
import com.credicar.backend.iam.interfaces.rest.resources.SignUpResource;

public class SignUpCommandFromResourceAssembler {

    public static SignUpCommand toCommandFromResource(SignUpResource resource) {
        return new SignUpCommand(resource.fullName(), resource.email(), resource.password());
    }
}
