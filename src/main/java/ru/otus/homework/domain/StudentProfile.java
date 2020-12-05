package ru.otus.homework.domain;

import java.util.List;

public class StudentProfile {
    private final String firstname;
    private final String lastname;
    private List<Answer> givenAnswers;
    private List<Question> failedQuestions;
    private boolean passed;

    public StudentProfile(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public List<Answer> getGivenAnswers() {
        return givenAnswers;
    }

    public List<Question> getFailedQuestions() {
        return failedQuestions;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setGivenAnswers(List<Answer> givenAnswers) {
        this.givenAnswers = givenAnswers;
    }

    public void setFailedQuestions(List<Question> failedQuestions) {
        this.failedQuestions = failedQuestions;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }
}
