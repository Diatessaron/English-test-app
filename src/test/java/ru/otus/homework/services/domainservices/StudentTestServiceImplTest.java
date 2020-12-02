package ru.otus.homework.services.domainservices;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework.domain.Answer;
import ru.otus.homework.domain.Question;
import ru.otus.homework.services.applicationservices.ConsoleService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentTestServiceImplTest {
    @Mock
    private ConsoleService consoleService;
    @Mock
    private QuestionReaderService questionReaderService;

    private StudentTestService studentTestService;
    private static List<Question> questionList;
    private static int rightAnswerCount;
    private static List<String> correctAnswerStringList;

    @BeforeAll
    static void fieldsSetUp(){
        rightAnswerCount = 1;
        correctAnswerStringList = new ArrayList<>();
        questionList = new ArrayList<>();

        correctAnswerStringList.add("your");
        correctAnswerStringList.add("from");
        correctAnswerStringList.add("very");
        correctAnswerStringList.add("Have you got");
        correctAnswerStringList.add("doesn't");

        questionList.add(new Question(1, "What's .... name?", List.of(
                new Answer("you"), new Answer("she"), new Answer("your"), new Answer("yours")
        )));
        questionList.add(new Question(2, "We're Chinese. We're ... Beijing", List.of(
                new Answer("for"), new Answer("from"), new Answer("in"), new Answer("at")
        )));
        questionList.add(new Question(3, "Jane's .... nice and polite.", List.of(
                new Answer("a"), new Answer("from"), new Answer("very"), new Answer("at")
        )));
        questionList.add(new Question(4, ".... a light?", List.of(
                new Answer("Do have you"), new Answer("Do you got"), new Answer("Have you got"), new Answer("Are you have")
        )));
        questionList.add(new Question(5, "Margaret ..... usually come by bus", List.of(
                new Answer("doesn't"), new Answer("isn't"), new Answer("don't"), new Answer("aren't")
        )));
    }

    @BeforeEach
    void setUp(){
        studentTestService = new StudentTestServiceImpl(questionReaderService, consoleService,
                correctAnswerStringList, rightAnswerCount);
        when(questionReaderService.getQuestions()).thenReturn(questionList);
    }

    @Test
    void testStudentByTimesOfReadMethodInvocation() {
        studentTestService.testStudent();

        final int expected = 7;
        verify(consoleService, times(expected)).read();
    }

    @Test
    void testStudentByTimesOfPrintMethodInvocation(){
        studentTestService.testStudent();

        final int expected = 16;
        verify(consoleService, times(expected)).print(any(String.class));
    }
}
