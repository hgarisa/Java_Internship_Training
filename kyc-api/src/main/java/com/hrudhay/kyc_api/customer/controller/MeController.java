package com.hrudhay.kyc_api.customer.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class MeController {

    @GetMapping("/me")
    public Map<String, Object> me(@AuthenticationPrincipal Jwt jwt) {

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("sub", jwt.getSubject());
        result.put("preferred_username", jwt.getClaimAsString("preferred_username"));
        result.put("email", jwt.getClaimAsString("email"));

        // realm roles from token
        Map<String, Object> realmAccess = jwt.getClaim("realm_access");
        List<String> roles = List.of();

        if (realmAccess != null) {
            Object rolesObj = realmAccess.get("roles");
            if (rolesObj instanceof Collection<?> c) {
                roles = c.stream().map(Object::toString).toList();
            }
        }

        result.put("realm_roles", roles);
        result.put("isAdmin", roles.contains("admin"));
        result.put("isCustomer", roles.contains("customer"));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<String> authorities = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        result.put("authorities", authorities);

        return result;
    }
}
