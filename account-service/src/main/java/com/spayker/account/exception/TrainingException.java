package com.spayker.account.exception;

/**
 *  A custom exception class that works with training service layer of account micro-service.
 **/
public class TrainingException extends RuntimeException {

    public TrainingException() { super(); }

    public TrainingException(String message) { super(message); }

    public TrainingException(String message, Throwable cause) { super(message, cause); }

    public TrainingException(Throwable cause) {
        super(cause);
    }
    
    
}
