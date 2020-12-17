package ru.otus.homework.services.domainservices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.support.ResourceBundleMessageSource;
import ru.otus.homework.domain.Answer;
import ru.otus.homework.domain.Question;
import ru.otus.homework.domain.StudentProfile;
import ru.otus.homework.services.domainservices.utility.InputOutputService;
import ru.otus.homework.services.domainservices.utility.LocalizationPrintService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentTestServiceImplTest {
    @Mock
    private InputOutputService inputOutputService;
    @Mock
    private QuestionReaderService questionReaderService;
    @Mock
    private LocalizationPrintService localizationPrintService;

    private StudentTestService studentTestService;
    private static final int RIGHT_ANSWER_COUNT = 3;

    @BeforeEach
    void setUp() {
        final ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasenames("resources/lang/labels");
        source.setUseCodeAsDefaultMessage(true);

        studentTestService = new StudentTestServiceImpl(questionReaderService, inputOutputService,
                RIGHT_ANSWER_COUNT, localizationPrintService);

        List<Question> questionList = List.of(new Question(1, "What's .... name?", List.of(
                new Answer("you"), new Answer("she"),
                new Answer("your"), new Answer("yours")
                ), new Answer("your")),
                new Question(2, "We're Chinese. We're ... Beijing", List.of(
                        new Answer("for"), new Answer("from"),
                        new Answer("in"), new Answer("at")
                ), new Answer("from")),
                new Question(3, "Jane's .... nice and polite.", List.of(
                        new Answer("a"), new Answer("from"),
                        new Answer("very"), new Answer("at")
                ), new Answer("very")),
                new Question(4, ".... a light?", List.of(
                        new Answer("Do have you"), new Answer("Do you got"),
                        new Answer("Have you got"), new Answer("Are you have")
                ), new Answer("Have you got")),
                new Question(5, "Margaret ..... usually come by bus", List.of(
                        new Answer("doesn't"), new Answer("isn't"),
                        new Answer("don't"), new Answer("aren't")
                ), new Answer("doesn't"))
        );

        doNothing().when(localizationPrintService).printMessage(any(), any());
        when(questionReaderService.getQuestions()).thenReturn(questionList);
        when(inputOutputService.read()).thenReturn("Firstname", "Lastname", "your", "from", "very", "Have you got", "doesn't");
    }

    @Test
    void testByOrderOfMocksMethodsInvocation() {
        studentTestService.testStudent();

        final InOrder inOrder = inOrder(inputOutputService, questionReaderService, localizationPrintService);

        for (int i = 0; i < 2; i++) {
            inOrder.verify(localizationPrintService).printMessage(anyString());
            inOrder.verify(inputOutputService).read();
        }
        inOrder.verify(localizationPrintService).printMessage(anyString(), anyInt());

        inOrder.verify(questionReaderService).getQuestions();

        for (int i = 0; i < 5; i++) {
            inOrder.verify(inputOutputService).print(anyString());
            inOrder.verify(inputOutputService).read();
        }
    }

    @Test
    void testStudentMethodShouldReturnTrue() {
        final StudentProfile studentProfile = studentTestService.testStudent();

        assertTrue(studentProfile.isPassed());
    }
}
