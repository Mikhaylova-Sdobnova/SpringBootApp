package ru.mikhailova.julia.SpringBootApp.exeption_handling;

public class NoSuchUserException extends RuntimeException {
    public NoSuchUserException(String message) {
        super(message);
    }
}
