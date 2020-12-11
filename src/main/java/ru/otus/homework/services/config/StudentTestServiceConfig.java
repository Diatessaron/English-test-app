package ru.otus.homework.services.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.homework.services.domainservices.utility.InputOutputService;
import ru.otus.homework.services.domainservices.QuestionReaderService;
import ru.otus.homework.services.domainservices.StudentTestService;
import ru.otus.homework.services.domainservices.StudentTestServiceImpl;
import ru.otus.homework.services.domainservices.utility.LocalizationPrintService;

@Configuration
public class StudentTestServiceConfig {
    private final int rightAnswersCount;

    public StudentTestServiceConfig(AppProps appProps) {
        this.rightAnswersCount = appProps.getRightAnswerCount();
    }

    @Bean
    public StudentTestService studentTestService(QuestionReaderService questionReaderService,
                                                 InputOutputService inputOutputService,
                                                 LocalizationPrintService localizationPrintService) {

        return new StudentTestServiceImpl(questionReaderService,
                inputOutputService, rightAnswersCount, localizationPrintService);
    }
}
