package com.credicar.backend.iam.interfaces.acl;

import java.util.Optional;

/**
 * Anti-Corruption Layer facade for the IAM bounded context.
 * Other bounded contexts must use this interface to query user identity data.
 */
public interface IamContextFacade {
    Optional<Long> fetchUserIdByEmail(String email);
}
