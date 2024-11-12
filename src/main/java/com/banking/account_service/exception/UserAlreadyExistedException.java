package com.banking.account_service.exception;

public class UserAlreadyExistedException extends RuntimeException {

    public UserAlreadyExistedException(String message) {
        super(message);
    }
}
