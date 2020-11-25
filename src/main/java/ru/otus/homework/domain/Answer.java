package ru.otus.homework.domain;

public class Answer {
    private final String content;

    public Answer(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }
}
