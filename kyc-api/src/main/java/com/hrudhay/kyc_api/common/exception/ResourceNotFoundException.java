package com.hrudhay.kyc_api.common.exception;

public class ResourceNotFoundException extends RuntimeException
{

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
