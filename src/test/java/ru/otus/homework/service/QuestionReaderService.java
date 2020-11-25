package ru.otus.homework.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionReaderServiceTest {
    private static QuestionReaderService questionReaderService;

    @BeforeAll
    static void setUp(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        questionReaderService = context.getBean(QuestionReaderService.class);
    }

    @Test
    void testGetQuestionsBySize() {
        final int actual = questionReaderService.getQuestions().size();

        assertEquals(5, actual);
    }
}