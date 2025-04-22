package com.yasmiongv.autentication_api.exceptions.custom;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String message) {
        super(message);
    }
}
