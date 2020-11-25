package ru.otus.homework;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.homework.service.QuestionReaderService;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        QuestionReaderService questionReaderService = context.getBean(QuestionReaderService.class);

        System.out.println("Hello. You will get questions with answers. English test starts");
        questionReaderService.getQuestions().forEach(System.out::println);
    }
}
