package ru.otus.homework.exceptions;

public class QuestionReaderException extends RuntimeException {
    public QuestionReaderException(String message) {
        super(message);
    }

    public QuestionReaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
