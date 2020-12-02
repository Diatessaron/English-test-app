package ru.otus.homework.exceptions;

public class ConsoleServiceException extends RuntimeException{
    public ConsoleServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
