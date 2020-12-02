package ru.otus.homework.services.domainservices;

import ru.otus.homework.domain.Question;
import ru.otus.homework.services.applicationservices.ConsoleService;

import java.util.ArrayList;
import java.util.List;

public class StudentTestServiceImpl implements StudentTestService{
    private final QuestionReaderService questionReaderService;
    private final ConsoleService consoleService;
    private final int rightAnswersCount;
    private final List<String> correctAnswerStringList;

    public StudentTestServiceImpl(QuestionReaderService questionReaderService,
                                  ConsoleService consoleService, List<String> correctAnswerStringList,
                                  int rightAnswersCount) {
        this.questionReaderService = questionReaderService;
        this.consoleService = consoleService;
        this.correctAnswerStringList = correctAnswerStringList;
        this.rightAnswersCount = rightAnswersCount;
    }

    @Override
    public void testStudent() {
        consoleService.print("Hello! Please, enter your first name: ");
        final String firstname = consoleService.read();
        consoleService.print("Please, enter your last name: ");
        final String lastname = consoleService.read();

        consoleService.print("You will get questions with answers." +
                " You have to print answer in the console. At least, " + rightAnswersCount + " answers should be correct");
        consoleService.print("English test starts");

        List<Question> failedQuestions = new ArrayList<>();

        for(Question question : questionReaderService.getQuestions()){
            consoleService.print(question.toString());
            checkAnswer(question, consoleService.read(), failedQuestions);
        }

        printResult(firstname, lastname, failedQuestions);
    }

    private void checkAnswer(Question question, String answerStr, List<Question> failedQuestions){
        if(!correctAnswerStringList.get(question.getId()-1).equalsIgnoreCase(answerStr))
            failedQuestions.add(question);
    }

    private void printResult(String firstname, String lastname, List<Question> failedQuestions){
        if(failedQuestions.size() < rightAnswersCount)
            consoleService.print("Congratulations, " + firstname +
                    ' ' + lastname + "! You have passed the test.");
        else {
            consoleService.print("Unfortunately, " + firstname
                    + ", you failed the test. You have " + failedQuestions.size() +
                    " incorrect answers.");
        }

        if(!failedQuestions.isEmpty()) {
            consoleService.print("Here is the list of mistakes: ");
            failedQuestions.forEach(question -> consoleService.print("Question " + question.getId() +
                    ":\n" + question.getContent() + '\n'));
        }
    }
}
