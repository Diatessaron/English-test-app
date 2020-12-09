package ru.otus.homework;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.homework.services.applicationservices.AppStartService;
import ru.otus.homework.services.applicationservices.AppStartServiceImpl;

@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        AppStartService appStartService = context.getBean(AppStartServiceImpl.class);
        appStartService.start();
    }
}
