package com.hrudhay.kyc_api.customer.service;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OtpService {

    private static final long EXPIRY_SECONDS = 300; // 5 minutes
    private static final int MAX_ATTEMPTS = 5;

    private final Map<String, OtpSession> store = new ConcurrentHashMap<>();

    public String createSession(String email, String idNumber, String phoneNumber, String otp) {
        String sessionId = UUID.randomUUID().toString();
        store.put(sessionId, new OtpSession(email, idNumber, phoneNumber, otp, Instant.now().plusSeconds(EXPIRY_SECONDS), 0));
        return sessionId;
    }

    public boolean verify(String sessionId, String otp) {
        OtpSession s = store.get(sessionId);
        if (s == null) return false;

        if (Instant.now().isAfter(s.expiresAt())) {
            store.remove(sessionId);
            return false;
        }

        if (s.attempts() >= MAX_ATTEMPTS) {
            store.remove(sessionId);
            return false;
        }

        // increment attempts
        store.put(sessionId, s.withAttempts(s.attempts() + 1));

        if (!s.otp().equals(otp)) return false;

        // success - remove session
        store.remove(sessionId);
        return true;
    }

    public OtpSession getSession(String sessionId) {
        return store.get(sessionId);
    }

    public record OtpSession(String email, String idNumber, String phoneNumber, String otp, Instant expiresAt, int attempts) {
        public OtpSession withAttempts(int newAttempts) {
            return new OtpSession(email, idNumber, phoneNumber, otp, expiresAt, newAttempts);
        }
    }
}
