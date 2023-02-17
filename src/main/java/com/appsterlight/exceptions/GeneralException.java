package com.appsterlight.exceptions;

public class GeneralException extends RuntimeException {

    public GeneralException(String message) {
        super(message);
    }
    public GeneralException(Throwable cause) {
        super(cause);
    }

    public GeneralException(String message, Throwable cause) {
        super(message, cause);
    }
}
