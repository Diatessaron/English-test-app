package ru.otus.homework.domain;

import java.util.Objects;

public class Answer {
    private final String content;

    public Answer(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return content.equals(answer.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }
}
