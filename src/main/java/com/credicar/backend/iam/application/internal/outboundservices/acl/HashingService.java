package com.credicar.backend.iam.application.internal.outboundservices.acl;

public interface HashingService {
    String encode(CharSequence rawPassword);
    boolean matches(CharSequence rawPassword, String encodedPassword);
}
