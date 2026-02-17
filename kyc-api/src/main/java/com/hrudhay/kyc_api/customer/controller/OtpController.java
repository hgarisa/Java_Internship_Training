package com.hrudhay.kyc_api.customer.controller;

import com.hrudhay.kyc_api.customer.entity.Customer;
import com.hrudhay.kyc_api.customer.repo.CustomerRepository;
import com.hrudhay.kyc_api.customer.service.OtpRedisService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("/api/otp")
public class OtpController {

    private final OtpRedisService otpRedisService;
    private final CustomerRepository customerRepository;

    public OtpController(OtpRedisService otpRedisService, CustomerRepository customerRepository) {
        this.otpRedisService = otpRedisService;
        this.customerRepository = customerRepository;
    }

    @PostMapping("/send")
    public Map<String, Object> send(@AuthenticationPrincipal Jwt jwt) {
        String key = jwt.getSubject();
        String otp = otpRedisService.generateAndStoreOtpForUserKey(key);
        return Map.of("message", "OTP generated", "otp", otp); // dev only
    }

    @PostMapping("/verify")
    public Map<String, Object> verify(@AuthenticationPrincipal Jwt jwt,
                                      @RequestBody Map<String, Object> body) {

        String key = jwt.getSubject();

        Object otpRaw = body.getOrDefault("otp", body.get("code")); // accept otp OR code
        String otpInput = otpRaw == null ? "" : otpRaw.toString().trim();

        if (otpInput.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "OTP is required");
        }

        System.out.println("VERIFY called for sub=" + jwt.getSubject() + " otp=" + otpInput);
        boolean ok = otpRedisService.verifyOtpForUserKey(key, otpInput);
        System.out.println("VERIFY result sub=" + key + " ok=" + ok);
        return Map.of("verified", ok);
    }


    @PostMapping("/precheck")
    public Map<String, Object> precheck(@AuthenticationPrincipal Jwt jwt,
                                        @RequestBody Map<String, String> body) {

        String email = jwt.getClaimAsString("email"); // email is fine for DB lookup
        if (email == null) email = jwt.getClaimAsString("preferred_username");

        String idNumber = body.getOrDefault("idNumber", "").trim();
        String phoneNumber = body.getOrDefault("phoneNumber", "").trim();

        Customer customer = customerRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("Customer not found for logged-in user"));

        boolean match = idNumber.equalsIgnoreCase(customer.getIdNumber())
                && phoneNumber.equals(customer.getPhoneNumber());

        if (!match) throw new RuntimeException("ID/Phone mismatch");

        return Map.of("ok", true);
    }

    private String getEmail(Jwt jwt) {
        String email = jwt.getClaimAsString("email");
        if (email == null || email.isBlank()) email = jwt.getClaimAsString("preferred_username");
        if (email == null || email.isBlank()) email = jwt.getClaimAsString("username");
        if (email == null || email.isBlank()) email = jwt.getSubject();
        if (email == null || email.isBlank()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email not found in token");
        }
        return email.trim();
    }

    private String normalizePhone(String phone) {
        if (phone == null) return "";
        String p = phone.trim().replaceAll("\\s+", "");

        if (p.startsWith("+")) return p;

        if (p.startsWith("0")) p = p.substring(1);
        return "+27" + p;
    }
}
