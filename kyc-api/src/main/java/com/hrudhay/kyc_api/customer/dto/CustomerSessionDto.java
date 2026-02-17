package com.hrudhay.kyc_api.customer.dto;

public record CustomerSessionDto(

        Long id,
        String idNumber,
        String firstName,
        String lastName,
        String email,
        String phoneNumber
)
{

}
