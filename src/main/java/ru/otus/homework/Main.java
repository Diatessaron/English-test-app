package ru.otus.homework;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.homework.services.applicationservices.AppStartService;
import ru.otus.homework.services.applicationservices.AppStartServiceImpl;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        AppStartService appStartService = context.getBean(AppStartServiceImpl.class);
        appStartService.start();
    }
}
