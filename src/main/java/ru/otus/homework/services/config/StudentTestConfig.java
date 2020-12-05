package ru.otus.homework.services.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.homework.services.applicationservices.InputOutputService;
import ru.otus.homework.services.domainservices.QuestionReaderService;
import ru.otus.homework.services.domainservices.StudentTestService;
import ru.otus.homework.services.domainservices.StudentTestServiceImpl;

@Configuration
@ComponentScan("ru.otus.homework.services")
public class StudentTestConfig {
    private final int rightAnswersCount;

    public StudentTestConfig(@Value("3") int rightAnswersCount) {
        this.rightAnswersCount = rightAnswersCount;
    }

    @Bean
    public StudentTestService studentTestService(QuestionReaderService questionReaderService,
                                                 InputOutputService inputOutputService) {

        return new StudentTestServiceImpl(questionReaderService,
                inputOutputService, rightAnswersCount);
    }
}
