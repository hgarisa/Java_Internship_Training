package com.hrudhay.kyc_api.customer.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Duration;

@Service
public class OtpRedisService {

    private final StringRedisTemplate redis;
    private final SecureRandom random = new SecureRandom();

    public OtpRedisService(StringRedisTemplate redis) {
        this.redis = redis;
    }

    private String userKey(Jwt jwt) {
        return jwt.getSubject();
    }

    private String otpKey(String userKey) { return "otp:" + userKey; }
    private String otpOkKey(String userKey) { return "otp_ok:" + userKey; }

    public String generateAndStoreOtpForUserKey(String userKey) {
        redis.delete(otpOkKey(userKey));
        String otp = String.valueOf(100000 + random.nextInt(900000));
        redis.opsForValue().set(otpKey(userKey), otp, Duration.ofMinutes(5));
        System.out.println("DEV OTP for userKey(sub) " + userKey + " = " + otp);
        return otp;
    }

    public boolean verifyOtpForUserKey(String userKey, String otpInput) {
        String saved = redis.opsForValue().get(otpKey(userKey));
        if (saved == null) return false;

        boolean ok = saved.equals(otpInput);
        if (ok) {
            redis.delete(otpKey(userKey));
            redis.opsForValue().set(otpOkKey(userKey), "true", Duration.ofMinutes(15));
        }
        return ok;
    }

    public boolean isOtpVerified(String userKey) {
        return "true".equals(redis.opsForValue().get(otpOkKey(userKey)));
    }



    public void clearOtpVerified(String userKey) {
        redis.delete(otpOkKey(userKey));
    }
}

