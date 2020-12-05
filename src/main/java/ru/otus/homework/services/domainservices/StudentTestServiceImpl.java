package ru.otus.homework.services.domainservices;

import ru.otus.homework.domain.Answer;
import ru.otus.homework.domain.Question;
import ru.otus.homework.domain.StudentProfile;
import ru.otus.homework.services.applicationservices.InputOutputService;

import java.util.ArrayList;
import java.util.List;

public class StudentTestServiceImpl implements StudentTestService {
    private final QuestionReaderService questionReaderService;
    private final InputOutputService inputOutputService;
    private final int rightAnswerCount;

    public StudentTestServiceImpl(QuestionReaderService questionReaderService,
                                  InputOutputService inputOutputService,
                                  int rightAnswerCount) {
        this.questionReaderService = questionReaderService;
        this.inputOutputService = inputOutputService;
        this.rightAnswerCount = rightAnswerCount;
    }

    @Override
    public StudentProfile testStudent() {
        final StudentProfile studentProfile = getStudentData();
        beginTest();

        final List<Question> questions = questionReaderService.getQuestions();
        List<Answer> givenAnswers = new ArrayList<>();

        for (Question question : questions) {
            inputOutputService.print(question.toString());
            givenAnswers.add(new Answer(inputOutputService.read()));
        }

        final List<Question> failedQuestions = checkAnswers(givenAnswers, questions);

        final boolean passed = failedQuestions.size() <= questions.size() - rightAnswerCount;

        studentProfile.setGivenAnswers(givenAnswers);
        studentProfile.setFailedQuestions(failedQuestions);
        studentProfile.setPassed(passed);

        return studentProfile;
    }

    private List<Question> checkAnswers(List<Answer> givenAnswers, List<Question> questions) {
        List<Question> failedQuestions = new ArrayList<>();

        for (int i = 0; i < givenAnswers.size(); i++) {
            if (!givenAnswers.get(i).toString().equalsIgnoreCase(questions.get(i)
                    .getCorrectAnswer().toString()))
                failedQuestions.add(questions.get(i));
        }

        return failedQuestions;
    }

    private StudentProfile getStudentData() {
        inputOutputService.print("Hello! Please, enter your first name: ");
        final String firstname = inputOutputService.read();
        inputOutputService.print("Please, enter your last name: ");
        final String lastname = inputOutputService.read();

        return new StudentProfile(firstname, lastname);
    }

    private void beginTest() {
        inputOutputService.print(String.format("You will get questions with answers." +
                " You have to print answer in the console. At least, %d answers should be correct\n" +
                "English test starts", rightAnswerCount));
    }
}
