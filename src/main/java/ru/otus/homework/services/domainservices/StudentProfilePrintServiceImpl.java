package ru.otus.homework.services.domainservices;

import org.springframework.stereotype.Service;
import ru.otus.homework.domain.Question;
import ru.otus.homework.domain.StudentProfile;
import ru.otus.homework.services.applicationservices.InputOutputService;

import java.util.List;

@Service
public class StudentProfilePrintServiceImpl implements StudentProfilePrintService {
    private final InputOutputService inputOutputService;

    public StudentProfilePrintServiceImpl(InputOutputService inputOutputService) {
        this.inputOutputService = inputOutputService;
    }

    @Override
    public void printStudentResult(StudentProfile studentProfile) {
        final List<Question> failedQuestions = studentProfile.getFailedQuestions();

        if (studentProfile.isPassed())
            inputOutputService.print(String.format("Congratulations, %s %s! You have passed the test.",
                    studentProfile.getFirstname(), studentProfile.getLastname()));
        else {
            inputOutputService.print(String.format("Unfortunately, %s %s, you failed the test." +
                            " You have %d incorrect answers.",
                    studentProfile.getFirstname(), studentProfile.getLastname(), failedQuestions.size()));
        }

        if (!failedQuestions.isEmpty()) {
            inputOutputService.print("You have some mistakes. Here are they: ");
            for (int i = 0; i < failedQuestions.size(); i++) {
                final Question question = failedQuestions.get(i);
                inputOutputService.print(String.format("Question %d:\n%s\nThe correct answer is \"%s\"\n" +
                                "But your answer was: \"%s\"\n",
                        question.getId(), question.getContent(), question.getCorrectAnswer(),
                        studentProfile.getGivenAnswers().get(i).toString()));
            }
        }
    }
}
