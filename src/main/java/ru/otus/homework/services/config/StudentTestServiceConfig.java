package ru.otus.homework.services.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.homework.services.domainservices.InputOutputService;
import ru.otus.homework.services.domainservices.QuestionReaderService;
import ru.otus.homework.services.domainservices.StudentTestService;
import ru.otus.homework.services.domainservices.StudentTestServiceImpl;

@Configuration
public class StudentTestServiceConfig {
    private final int rightAnswersCount;

    public StudentTestServiceConfig(@Value("${application.rightAnswerCount}") int rightAnswersCount) {
        this.rightAnswersCount = rightAnswersCount;
    }

    @Bean
    public StudentTestService studentTestService(QuestionReaderService questionReaderService,
                                                 InputOutputService inputOutputService, MessageSource messageSource,
                                                 AppProps appProps) {

        return new StudentTestServiceImpl(questionReaderService,
                inputOutputService, rightAnswersCount, messageSource, appProps);
    }
}
