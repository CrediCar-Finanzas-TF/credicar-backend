package com.credicar.backend.iam.infrastructure.hashing.bcrypt;

import com.credicar.backend.iam.application.internal.outboundservices.acl.HashingService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Implements both {@link HashingService} (domain outbound port) and {@link PasswordEncoder}
 * (Spring Security contract) so a single bean satisfies both injection points.
 */
@Service
public class BCryptHashingService implements HashingService, PasswordEncoder {

    private final BCryptPasswordEncoder delegate = new BCryptPasswordEncoder();

    @Override
    public String encode(CharSequence rawPassword) {
        return delegate.encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return delegate.matches(rawPassword, encodedPassword);
    }
}
