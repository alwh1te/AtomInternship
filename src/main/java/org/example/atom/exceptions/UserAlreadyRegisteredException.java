package org.example.atom.exceptions;

public class UserAlreadyRegisteredException extends UnexpectedItemException {
    public UserAlreadyRegisteredException(String message) {
        super(message);
    }
}
