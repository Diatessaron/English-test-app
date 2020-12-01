package ru.otus.homework.services.applicationservices;

import ru.otus.homework.services.domainservices.QuestionReaderService;

public class AppStartServiceImpl implements AppStartService {
    private final QuestionReaderService questionReader;
    private final ConsoleService consoleService;

    public AppStartServiceImpl(QuestionReaderService questionReader, ConsoleService consoleService) {
        this.questionReader = questionReader;
        this.consoleService = consoleService;
    }

    @Override
    public void start() {
        testStudent();
    }

    private void testStudent(){
        consoleService.print("Hello. You will get questions with answers. English test starts");
        questionReader.getQuestions().forEach(question -> consoleService.print(question.toString()));
    }
}
