package ru.otus.homework.domain;

import java.util.List;
import java.util.Objects;

public class Question {
    private final int id;
    private final String content;
    private final List<Answer> answerList;
    private final Answer correctAnswer;

    public Question(int id, String content, List<Answer> answerList, Answer correctAnswer) {
        this.id = id;
        this.content = content;
        this.answerList = answerList;
        this.correctAnswer = correctAnswer;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Answer getCorrectAnswer() {
        return correctAnswer;
    }

    @Override
    public String toString() {
        StringBuilder answersInLine = new StringBuilder();

        for (Answer answer : answerList) {
            answersInLine.append(answer.toString()).append('\t');
        }

        return "Question " + id +
                ":\n" + content +
                '\n' + answersInLine.toString() + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return id == question.id &&
                content.equals(question.content) &&
                answerList.equals(question.answerList) &&
                correctAnswer.equals(question.correctAnswer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, answerList, correctAnswer);
    }
}
