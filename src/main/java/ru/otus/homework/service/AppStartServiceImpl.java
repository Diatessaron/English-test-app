package ru.otus.homework.service;

public class AppStartServiceImpl implements AppStartService {
    private final QuestionReaderService questionReader;

    public AppStartServiceImpl(QuestionReaderService questionReader) {
        this.questionReader = questionReader;
    }

    @Override
    public void start() {
        System.out.println("Hello. You will get questions with answers. English test starts");
        questionReader.getQuestions().forEach(System.out::println);
    }
}
