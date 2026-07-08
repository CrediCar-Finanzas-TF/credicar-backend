package com.credicar.backend.iam.application.internal.commandservices;

import com.credicar.backend.iam.application.internal.outboundservices.acl.HashingService;
import com.credicar.backend.iam.application.internal.outboundservices.acl.TokenService;
import com.credicar.backend.iam.domain.exceptions.UserAlreadyExistsException;
import com.credicar.backend.iam.domain.exceptions.UserNotFoundException;
import com.credicar.backend.iam.domain.model.aggregates.User;
import com.credicar.backend.iam.domain.model.commands.SignInCommand;
import com.credicar.backend.iam.domain.model.commands.SignUpCommand;
import com.credicar.backend.iam.domain.services.UserCommandService;
import com.credicar.backend.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.AbstractMap;
import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;

    public UserCommandServiceImpl(UserRepository userRepository,
                                  HashingService hashingService,
                                  TokenService tokenService) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
    }

    @Override
    @Transactional
    public Optional<User> handle(SignUpCommand command) {
        if (userRepository.existsByEmail(command.email())) {
            throw new UserAlreadyExistsException(command.email());
        }
        var hashedPassword = hashingService.encode(command.password());
        var user = new User(command.fullName(), command.email(), hashedPassword);
        userRepository.save(user);
        return Optional.of(user);
    }

    @Override
    public Optional<AbstractMap.SimpleImmutableEntry<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByEmail(command.email())
                .orElseThrow(() -> new UserNotFoundException(command.email()));
        if (!hashingService.matches(command.password(), user.getPassword())) {
            return Optional.empty();
        }
        var token = tokenService.generateToken(user.getEmail());
        return Optional.of(new AbstractMap.SimpleImmutableEntry<>(user, token));
    }
}
