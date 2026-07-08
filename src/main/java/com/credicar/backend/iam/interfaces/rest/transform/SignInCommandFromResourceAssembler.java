package com.credicar.backend.iam.interfaces.rest.transform;

import com.credicar.backend.iam.domain.model.commands.SignInCommand;
import com.credicar.backend.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {

    public static SignInCommand toCommandFromResource(SignInResource resource) {
        return new SignInCommand(resource.email(), resource.password());
    }
}
