package ru.otus.homework.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.FileSystemResource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionReaderServiceTest {
    private static QuestionReaderServiceImpl questionReaderServiceImpl;

    @BeforeAll
    static void setUp() {
        questionReaderServiceImpl = new QuestionReaderServiceImpl(
                new FileSystemResource("src/test/resources/testQuestionsWithAnswers.csv")
        );
    }

    @Test
    void testGetQuestionsBySize() {
        final int actual = questionReaderServiceImpl.getQuestions().size();

        assertEquals(5, actual);
    }
}
