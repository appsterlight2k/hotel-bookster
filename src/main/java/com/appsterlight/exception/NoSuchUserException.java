package com.appsterlight.exception;

public class NoSuchUserException extends ServiceException {
    public NoSuchUserException() {
        super("Account doesn't exist");
    }

}
