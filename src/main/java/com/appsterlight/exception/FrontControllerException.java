package com.appsterlight.exception;

public class FrontControllerException extends Exception {
    public FrontControllerException(String message) {
        super(message);
    }

    public FrontControllerException(String message, Throwable cause) {
        super(message, cause);
    }

    public FrontControllerException(Throwable cause) {
        super(cause);
    }
}
