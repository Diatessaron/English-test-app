package ru.otus.homework.services.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.Locale;

@ConstructorBinding
@ConfigurationProperties(prefix = "application")
public class AppProps {
    private final Locale locale;
    private final int rightAnswerCount;
    private final Resource resource;

    public AppProps(Locale locale, int rightAnswerCount, String resource) {
        this.locale = locale;
        this.rightAnswerCount = rightAnswerCount;
        this.resource = new ClassPathResource(String.format(resource, locale.getLanguage()));
    }

    public Resource getResource() {
        return resource;
    }

    public Locale getLocale() {
        return locale;
    }

    public int getRightAnswerCount() {
        return rightAnswerCount;
    }
}
