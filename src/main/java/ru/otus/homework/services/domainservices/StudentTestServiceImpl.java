package ru.otus.homework.services.domainservices;

import org.springframework.context.MessageSource;
import ru.otus.homework.domain.Answer;
import ru.otus.homework.domain.Question;
import ru.otus.homework.domain.StudentProfile;
import ru.otus.homework.services.config.AppProps;

import java.util.ArrayList;
import java.util.List;

public class StudentTestServiceImpl implements StudentTestService {
    private final QuestionReaderService questionReaderService;
    private final InputOutputService inputOutputService;
    private final int rightAnswerCount;
    private final MessageSource messageSource;
    private final AppProps appProps;

    public StudentTestServiceImpl(QuestionReaderService questionReaderService,
                                  InputOutputService inputOutputService,
                                  int rightAnswerCount, MessageSource messageSource,
                                  AppProps appProps) {
        this.questionReaderService = questionReaderService;
        this.inputOutputService = inputOutputService;
        this.rightAnswerCount = rightAnswerCount;
        this.messageSource = messageSource;
        this.appProps = appProps;
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
        inputOutputService.print(messageSource.getMessage("firstname", null, appProps.getLocale()));
        final String firstname = inputOutputService.read();
        inputOutputService.print(messageSource.getMessage("lastname", null, appProps.getLocale()));
        final String lastname = inputOutputService.read();

        return new StudentProfile(firstname, lastname);
    }

    private void beginTest() {
        inputOutputService.print(messageSource.getMessage("beginTest",
                new Object[] {rightAnswerCount}, appProps.getLocale()));
    }
}
