package com.hrudhay.kyc_api.customer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CustomerVerifyRequest(

        @NotBlank
        @Size(min = 3, max = 50)
        @Pattern(
                regexp = "^[A-Za-z0-9-]+$",
                message = "idNumber is incorrect"
        )
        String idNumber,

        @NotBlank
        @Size(min = 7, max = 30)
        @Pattern(regexp = "^[0-9+()\\-\\s]+$")
        String phoneNumber

) {}
