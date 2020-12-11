package ru.otus.homework.services.domainservices;

import org.springframework.stereotype.Service;
import ru.otus.homework.domain.Question;
import ru.otus.homework.domain.StudentProfile;
import ru.otus.homework.services.domainservices.utility.LocalizationPrintService;

import java.util.List;

@Service
public class StudentProfilePrintServiceImpl implements StudentProfilePrintService {
    private final LocalizationPrintService localizationPrintService;

    public StudentProfilePrintServiceImpl(LocalizationPrintService localizationPrintService) {
        this.localizationPrintService = localizationPrintService;
    }

    @Override
    public void printStudentResult(StudentProfile studentProfile) {
        final List<Question> failedQuestions = studentProfile.getFailedQuestions();

        if (studentProfile.isPassed())
            localizationPrintService.printMessage("test.passed",
                    studentProfile.getFirstname(), studentProfile.getLastname());
        else
            localizationPrintService.printMessage("test.failed", studentProfile.getFirstname(),
                    studentProfile.getLastname(), failedQuestions.size());

        if (!failedQuestions.isEmpty()) {
            localizationPrintService.printMessage("test.mistakes");
            for (final Question question : failedQuestions) {
                localizationPrintService.printMessage("test.failedQuestion", question.getId(),
                        question.getContent(), question.getCorrectAnswer(), studentProfile.getGivenAnswers()
                                .get(question.getId() - 1).toString());
            }
        }
    }
}
