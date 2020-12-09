package ru.otus.homework.services.domainservices;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.Question;
import ru.otus.homework.domain.StudentProfile;
import ru.otus.homework.services.config.AppProps;

import java.util.List;

@Service
public class StudentProfilePrintServiceImpl implements StudentProfilePrintService {
    private final InputOutputService inputOutputService;
    private final MessageSource messageSource;
    private final AppProps appProps;

    public StudentProfilePrintServiceImpl(InputOutputService inputOutputService, MessageSource messageSource,
                                          AppProps appProps) {
        this.inputOutputService = inputOutputService;
        this.messageSource = messageSource;
        this.appProps = appProps;
    }

    @Override
    public void printStudentResult(StudentProfile studentProfile) {
        final List<Question> failedQuestions = studentProfile.getFailedQuestions();

        if (studentProfile.isPassed())
            inputOutputService.print(messageSource.getMessage("test.passed",
                    new Object[]{studentProfile.getFirstname(), studentProfile.getLastname()}, appProps.getLocale()));
        else
            inputOutputService.print(messageSource.getMessage("test.failed", new Object[]
                    {studentProfile.getFirstname(), studentProfile.getLastname(), failedQuestions.size()}, appProps.getLocale()));

        if (!failedQuestions.isEmpty()) {
            inputOutputService.print(messageSource.getMessage("test.mistakes", null, appProps.getLocale()));
            for (final Question question : failedQuestions) {
                inputOutputService.print(messageSource.getMessage("test.failedQuestion", new Object[]
                        {question.getId(), question.getContent(), question.getCorrectAnswer(),
                                studentProfile.getGivenAnswers().get(question.getId() - 1).toString()}, appProps.getLocale()));
            }
        }
    }
}
