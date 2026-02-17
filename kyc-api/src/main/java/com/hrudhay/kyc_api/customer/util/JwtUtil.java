package com.hrudhay.kyc_api.customer.util;

import org.springframework.security.oauth2.jwt.Jwt;

public final class JwtUtil {
    private JwtUtil() {}

    public static String email(Jwt jwt) {
        if (jwt == null) return null;
        String email = jwt.getClaimAsString("email");
        if (email == null || email.isBlank()) {
            email = jwt.getClaimAsString("preferred_username");
        }
        return email == null ? null : email.trim().toLowerCase();
    }
}
