package ru.otus.homework.services.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import ru.otus.homework.exceptions.QuestionReaderException;
import ru.otus.homework.services.applicationservices.ConsoleService;
import ru.otus.homework.services.domainservices.QuestionReaderService;
import ru.otus.homework.services.domainservices.StudentTestService;
import ru.otus.homework.services.domainservices.StudentTestServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan("ru.otus.homework.services")
public class StudentTestConfig {
    private final int rightAnswersCount = 3;
    private final Resource resource;

    public StudentTestConfig(@Value("classpath:data/correctAnswers.txt") Resource resource) {
        this.resource = resource;
    }

    @Bean
    public StudentTestService studentTestService(QuestionReaderService questionReaderService,
                                                 ConsoleService consoleService) {
        List<String> correctAnswerStringList = new ArrayList<>();
        fillListWithDataFromFile(correctAnswerStringList);

        return new StudentTestServiceImpl(questionReaderService,
                consoleService, correctAnswerStringList, rightAnswersCount);
    }

    private void fillListWithDataFromFile(List<String> correctAnswerStringList) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            String row;

            while ((row = bufferedReader.readLine()) != null) {
                correctAnswerStringList.add(row);
            }
        } catch (IOException e) {
            throw new QuestionReaderException(e.toString(), e);
        }
    }
}
