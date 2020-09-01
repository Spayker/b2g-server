package com.spayker.account.exception;

/**
 *  A custom exception class that works with account service layer of account micro-service.
 **/
public class AccountException extends RuntimeException {

    public AccountException() {
        super();
    }

    public AccountException(String message) {
        super(message);
    }

    public AccountException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountException(Throwable cause) {
        super(cause);
    }

}
