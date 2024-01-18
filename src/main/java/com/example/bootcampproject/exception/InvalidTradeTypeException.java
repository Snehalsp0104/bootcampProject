package com.example.bootcampproject.exception;

public class InvalidTradeTypeException extends RuntimeException{
    public InvalidTradeTypeException() {
        super();
    }

    public InvalidTradeTypeException(String message) {
        super(message);
    }

    public InvalidTradeTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidTradeTypeException(Throwable cause) {
        super(cause);
    }

    protected InvalidTradeTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
