package com.credicar.backend.iam.interfaces.rest;

import com.credicar.backend.iam.domain.services.UserCommandService;
import com.credicar.backend.iam.interfaces.rest.resources.AuthenticatedUserResource;
import com.credicar.backend.iam.interfaces.rest.resources.SignInResource;
import com.credicar.backend.iam.interfaces.rest.resources.SignUpResource;
import com.credicar.backend.iam.interfaces.rest.resources.UserResource;
import com.credicar.backend.iam.interfaces.rest.transform.AuthenticatedUserResourceFromEntityAssembler;
import com.credicar.backend.iam.interfaces.rest.transform.SignInCommandFromResourceAssembler;
import com.credicar.backend.iam.interfaces.rest.transform.SignUpCommandFromResourceAssembler;
import com.credicar.backend.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/authentication", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "User registration and authentication endpoints")
public class AuthenticationController {

    private final UserCommandService userCommandService;

    public AuthenticationController(UserCommandService userCommandService) {
        this.userCommandService = userCommandService;
    }

    @PostMapping("/sign-up")
    @Operation(
            summary = "Register a new user",
            description = "Creates a new user account with the provided full name, email, and password.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Email already in use or invalid request body"),
    })
    public ResponseEntity<UserResource> signUp(@Valid @RequestBody SignUpResource resource) {
        var command = SignUpCommandFromResourceAssembler.toCommandFromResource(resource);
        return userCommandService.handle(command)
                .map(user -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(UserResourceFromEntityAssembler.toResourceFromEntity(user)))
                .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping("/sign-in")
    @Operation(
            summary = "Authenticate a user",
            description = "Validates the credentials and returns a JWT bearer token on success.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Authentication successful"),
            @ApiResponse(responseCode = "401", description = "Invalid email or password"),
    })
    public ResponseEntity<AuthenticatedUserResource> signIn(@Valid @RequestBody SignInResource resource) {
        var command = SignInCommandFromResourceAssembler.toCommandFromResource(resource);
        return userCommandService.handle(command)
                .map(pair -> ResponseEntity.ok(
                        AuthenticatedUserResourceFromEntityAssembler.toResourceFromEntity(
                                pair.getKey(), pair.getValue())))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}
