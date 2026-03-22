package com.capsule.corp.common.exceptions;

public class AccountClosedException extends RuntimeException {
    public AccountClosedException(String message) {
        super(message);
    }
}
