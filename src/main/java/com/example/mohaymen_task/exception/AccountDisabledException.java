package com.example.mohaymen_task.exception;

public class AccountDisabledException extends RuntimeException{
    public AccountDisabledException(String message) {
        super(message);
    }
}
