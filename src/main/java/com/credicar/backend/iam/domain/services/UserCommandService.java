package com.credicar.backend.iam.domain.services;

import com.credicar.backend.iam.domain.model.aggregates.User;
import com.credicar.backend.iam.domain.model.commands.SignInCommand;
import com.credicar.backend.iam.domain.model.commands.SignUpCommand;

import java.util.AbstractMap;
import java.util.Optional;

public interface UserCommandService {
    Optional<User> handle(SignUpCommand command);
    Optional<AbstractMap.SimpleImmutableEntry<User, String>> handle(SignInCommand command);
}
