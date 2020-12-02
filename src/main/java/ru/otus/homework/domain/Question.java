package ru.otus.homework.domain;

import java.util.List;

public class Question {
    private final int id;
    private final String content;
    private final List<Answer> answerList;

    public Question(int id, String content, List<Answer> answerList) {
        this.id = id;
        this.content = content;
        this.answerList = answerList;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public List<Answer> getAnswerList() {
        return answerList;
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
}
