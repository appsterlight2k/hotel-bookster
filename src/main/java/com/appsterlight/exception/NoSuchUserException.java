package com.appsterlight.exception;

import com.appsterlight.exception.FrontControllerException;

public class NoSuchUserException extends ServiceException {
    public NoSuchUserException() {
        super("Account doesn't exist");
    }

}
