package ru.otus.homework.services.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.homework.services.applicationservices.ConsoleService;
import ru.otus.homework.services.applicationservices.ConsoleServiceImpl;

@Configuration
public class ConsoleServiceConfig {
    @Bean
    public ConsoleService consoleServiceImpl(){
        return new ConsoleServiceImpl(System.in, System.out);
    }
}
