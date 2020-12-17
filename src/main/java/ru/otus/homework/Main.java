package ru.otus.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import ru.otus.homework.services.applicationservices.AppStartService;
import ru.otus.homework.services.config.AppProps;

@SpringBootApplication
@EnableConfigurationProperties(AppProps.class)
public class Main {
    public static void main(String[] args) {
        final ApplicationContext context = SpringApplication.run(Main.class, args);

        AppStartService appStartService = context.getBean(AppStartService.class);
        appStartService.start();
    }
}
