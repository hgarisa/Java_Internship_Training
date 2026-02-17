package com.hrudhay.kyc_api.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CustomerCreateRequest(

        @NotBlank(message = "idNumber is required")
        @Size(min = 3, max = 50, message = "idNumber must be between 3 and 50 characters")
        @Pattern(
                regexp = "^[A-Za-z0-9-]+$",
                message = "idNumber is incorrect"
        )
        String idNumber,

        @NotBlank(message = "phoneNumber is required")
        @Size(min = 7, max = 30, message = "phoneNumber must be between 7 and 30 characters")
        @Pattern(regexp = "^[0-9+()\\-\\s]+$", message = "phoneNumber contains invalid characters")
        String phoneNumber,

        @NotBlank(message = "firstName is required")
        @Size(min = 2, max = 50, message = "firstName must be between 2 and 50 characters")
        String firstName,

        @NotBlank(message = "lastName is required")
        @Size(min = 2, max = 50, message = "lastName must be between 2 and 50 characters")
        String lastName,

        @NotBlank(message = "email is required")
        @Email(message = "email must be a valid email address")
        String email
) {}
