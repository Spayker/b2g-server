package com.spayker.consumer.exception;

/**
 *  A custom exception class that works with consumer service layer of consumer micro-service.
 **/
public class ConsumerException extends RuntimeException {

    public ConsumerException() {
        super();
    }

    public ConsumerException(String message) {
        super(message);
    }

    public ConsumerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConsumerException(Throwable cause) {
        super(cause);
    }
}
