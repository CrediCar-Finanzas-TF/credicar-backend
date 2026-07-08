package com.credicar.backend.iam.application.internal.queryservices;

import com.credicar.backend.iam.domain.model.aggregates.User;
import com.credicar.backend.iam.domain.model.queries.GetUserByEmailQuery;
import com.credicar.backend.iam.domain.model.queries.GetUserByIdQuery;
import com.credicar.backend.iam.domain.services.UserQueryService;
import com.credicar.backend.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.userId());
    }

    @Override
    public Optional<User> handle(GetUserByEmailQuery query) {
        return userRepository.findByEmail(query.email());
    }
}
