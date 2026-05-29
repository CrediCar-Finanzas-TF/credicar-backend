package com.credicar.backend.iam.application.internal.outboundservices.acl;

public interface TokenService {
    String generateToken(String username);
    String getUsernameFromToken(String token);
    boolean validateToken(String token);
}
