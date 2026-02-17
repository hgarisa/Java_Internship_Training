package com.hrudhay.kyc_api.config;

import com.hrudhay.kyc_api.customer.service.OtpRedisService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;

@Component
public class OtpVerifiedFilter extends OncePerRequestFilter {

    private final OtpRedisService otpRedisService;

    private static final Set<String> OTP_PROTECTED_PREFIXES = Set.of(
            "/api/customers" // ok, but must exclude otp endpoints as above
    );


    public OtpVerifiedFilter(OtpRedisService otpRedisService) {
        this.otpRedisService = otpRedisService;
    }



    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();

        if (path.startsWith("/actuator")) return true;

        // allow OTP endpoints
        if (path.startsWith("/api/otp")) return true;


        if (path.startsWith("/api/auth/session/init")) return true;

        return OTP_PROTECTED_PREFIXES.stream().noneMatch(path::startsWith);
    }




    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            filterChain.doFilter(request, response);
            return;
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String authHeader = request.getHeader("Authorization");
        System.out.println("FILTER DEBUG method=" + request.getMethod()
                + " path=" + request.getRequestURI()
                + " authHeader=" + (authHeader != null ? "present" : "missing")
                + " auth=" + auth);

        // If no JWT auth yet, let Spring Security handle it
        if (!(auth instanceof JwtAuthenticationToken jwtAuth)) {
            filterChain.doFilter(request, response);
            return;
        }

        String sub = jwtAuth.getToken().getSubject();
        boolean verified = otpRedisService.isOtpVerified(sub);

        System.out.println("FILTER CHECK path=" + request.getRequestURI()
                + " sub=" + sub + " verified=" + verified);

        if (verified) {
            filterChain.doFilter(request, response);
            return;
        }

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\":\"OTP verification required\"}");
    }




}
