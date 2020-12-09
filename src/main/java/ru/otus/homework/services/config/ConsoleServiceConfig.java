package ru.otus.homework.services.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.homework.services.applicationservices.InputOutputService;
import ru.otus.homework.services.applicationservices.ConsoleInputOutputServiceImpl;

@Configuration
public class ConsoleServiceConfig {
    @Bean
    public InputOutputService consoleServiceImpl() {
        return new ConsoleInputOutputServiceImpl(System.in, System.out);
    }
}
