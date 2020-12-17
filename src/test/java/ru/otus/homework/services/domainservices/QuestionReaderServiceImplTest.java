package ru.otus.homework.services.domainservices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.homework.domain.Answer;
import ru.otus.homework.domain.Question;
import ru.otus.homework.services.config.AppProps;

import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionReaderServiceImplTest {
    private QuestionReaderServiceImpl questionReaderServiceImpl;
    private List<Question> questionList;
    private static final int EXPECTED_QUESTION_LIST_SIZE = 5;

    @BeforeEach
    void setUp() {
        final AppProps appProps = new AppProps(new Locale("en"),
                3, "%sTestQuestionsWithAnswers.csv");

        questionReaderServiceImpl = new QuestionReaderServiceImpl(appProps);

        questionList = List.of(
                new Question(1, "What's .... name?", List.of(
                        new Answer("you"), new Answer("she"), new Answer("your"), new Answer("yours")
                ), new Answer("your")),
                new Question(2, "We're Chinese. We're ... Beijing", List.of(
                        new Answer("for"), new Answer("from"), new Answer("in"), new Answer("at")
                ), new Answer("from")),
                new Question(3, "Jane's .... nice and polite.", List.of(
                        new Answer("a"), new Answer("from"), new Answer("very"), new Answer("at")
                ), new Answer("very")),
                new Question(4, ".... a light?", List.of(
                        new Answer("Do have you"), new Answer("Do you got"), new Answer("Have you got"), new Answer("Are you have")
                ), new Answer("Have you got")),
                new Question(5, "Margaret ..... usually come by bus", List.of(
                        new Answer("doesn't"), new Answer("isn't"), new Answer("don't"), new Answer("aren't")
                ), new Answer("doesn't"))
        );
    }

    @Test
    void shouldReturnQuestionsListWithCorrectSize() {
        final int actual = questionReaderServiceImpl.getQuestions().size();

        assertEquals(EXPECTED_QUESTION_LIST_SIZE, actual);
    }

    @Test
    void testByListsComparison() {
        final List<Question> actual = questionReaderServiceImpl.getQuestions();

        assertEquals(questionList, actual);
    }

    @Test
    void shouldReturnQuestionsListWithCorrectContent() {
        final List<Question> actual = questionReaderServiceImpl.getQuestions();

        assertThat(actual).extractingResultOf("getContent")
                .contains("What's .... name?", "We're Chinese. We're ... Beijing", "Jane's .... nice and polite.",
                        ".... a light?", "Margaret ..... usually come by bus");
    }

    @Test
    void testQuestionsListByCorrectAnswerComparison() {
        final List<Question> actual = questionReaderServiceImpl.getQuestions();

        assertThat(actual).extracting("correctAnswer.content").
                contains("your", "from", "very", "Have you got", "doesn't");
    }
}
