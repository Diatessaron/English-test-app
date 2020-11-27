package ru.otus.homework;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.homework.service.AppStartService;
import ru.otus.homework.service.AppStartServiceImpl;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        AppStartService appStartService = context.getBean(AppStartServiceImpl.class);
        appStartService.start();
    }
}
