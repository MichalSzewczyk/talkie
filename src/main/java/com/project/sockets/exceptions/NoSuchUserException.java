package com.project.sockets.exceptions;

import java.io.IOException;

public class NoSuchUserException extends IOException {
    public NoSuchUserException(String message) {
        super(message);
    }
}
