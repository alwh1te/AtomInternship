package org.example.atom.exceptions;

public abstract class AuthException extends Exception {
    public AuthException(String message) {
        super(message);
    }
}
