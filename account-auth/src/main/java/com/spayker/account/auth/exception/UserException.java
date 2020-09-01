package com.spayker.account.auth.exception;

/**
 *  Business related custom exception to intercept situation when something is going wrong with service layer
 **/
public class UserException extends RuntimeException {

    public UserException() {
        super();
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserException(Throwable cause) {
        super(cause);
    }


}
