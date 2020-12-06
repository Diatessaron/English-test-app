package ru.otus.homework.services.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import ru.otus.homework.services.domainservices.QuestionReaderService;
import ru.otus.homework.services.domainservices.QuestionReaderServiceImpl;

@Configuration
@ComponentScan("ru.otus.homework.services")
@PropertySource("classpath:application.properties")
public class QuestionReaderServiceConfig {
    @Bean
    public QuestionReaderService questionReaderServiceImpl(@Value("${application.testQuestionsWithAnswers}") Resource resource){
        return new QuestionReaderServiceImpl(resource);
    }
}
