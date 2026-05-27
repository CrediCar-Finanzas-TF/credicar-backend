package com.credicar.backend.iam.interfaces.acl;

import com.credicar.backend.iam.domain.model.queries.GetUserByEmailQuery;
import com.credicar.backend.iam.domain.services.UserQueryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IamContextFacadeImpl implements IamContextFacade {

    private final UserQueryService userQueryService;

    public IamContextFacadeImpl(UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
    }

    @Override
    public Optional<Long> fetchUserIdByEmail(String email) {
        return userQueryService.handle(new GetUserByEmailQuery(email))
                .map(user -> user.getId());
    }
}
