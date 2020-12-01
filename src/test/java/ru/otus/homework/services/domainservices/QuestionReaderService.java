package ru.otus.homework.services.domainservices;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import ru.otus.homework.domain.Answer;
import ru.otus.homework.domain.Question;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionReaderServiceImplTest {
    private static QuestionReaderServiceImpl questionReaderServiceImpl;
    private static final List<Question> questionList = new ArrayList<>();
    private static final int EXPECTED_QUESTION_LIST_SIZE = 5;

    @BeforeAll
    static void setUp() {
        questionReaderServiceImpl = new QuestionReaderServiceImpl(
                new ClassPathResource("testQuestionsWithAnswers.csv")
        );

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

    @Test
    void testGetQuestionsBySize() {
        final int actual = questionReaderServiceImpl.getQuestions().size();

        assertEquals(EXPECTED_QUESTION_LIST_SIZE, actual);
    }

    @Test
    void testGetQuestionsByQuestionComparison() {
        final List<Question> actual = questionReaderServiceImpl.getQuestions();

        assertEquals(questionList.toString(), actual.toString());
    }
}
