package org.example.atom.exceptions;

public abstract class UnexpectedItemException extends Exception {
    public UnexpectedItemException(String message) {
        super(message);
    }
}
