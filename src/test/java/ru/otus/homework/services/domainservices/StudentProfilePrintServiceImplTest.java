package ru.otus.homework.services.domainservices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.homework.domain.Answer;
import ru.otus.homework.domain.Question;
import ru.otus.homework.domain.StudentProfile;
import ru.otus.homework.services.domainservices.utility.LocalizationPrintService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.inOrder;

@SpringBootTest
class StudentProfilePrintServiceImplTest {
    @MockBean
    private LocalizationPrintService localizationPrintService;

    private StudentProfilePrintServiceImpl studentProfilePrintService;

    @BeforeEach
    void setUp() {
        studentProfilePrintService = new StudentProfilePrintServiceImpl(localizationPrintService);
    }

    @Test
    void printPassedStudentResult() {
        final StudentProfile studentProfile = new StudentProfile("firstname", "lastname");
        studentProfile.setPassed(true);
        studentProfile.setFailedQuestions(new ArrayList<>());

        studentProfilePrintService.printStudentResult(studentProfile);

        final InOrder inOrder = inOrder(localizationPrintService);
        inOrder.verify(localizationPrintService).printMessage
                ("test.passed", "firstname", "lastname");
    }

    @Test
    void printFailedStudentResult(){
        final StudentProfile studentProfile = new StudentProfile("firstname", "lastname");
        studentProfile.setPassed(false);
        studentProfile.setGivenAnswers(List.of(new Answer("AnswerContent")));
        studentProfile.setFailedQuestions(List.of(new Question(1, "Content",
                List.of(new Answer("AnswerContent")), new Answer("CorrectAnswer"))));

        studentProfilePrintService.printStudentResult(studentProfile);

        final InOrder inOrder = inOrder(localizationPrintService);
        inOrder.verify(localizationPrintService).printMessage("test.failed",
                "firstname", "lastname", 1);
        inOrder.verify(localizationPrintService).printMessage("test.mistakes");
    }
}
