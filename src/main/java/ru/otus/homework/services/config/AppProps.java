package ru.otus.homework.services.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

import java.util.Locale;

@ConfigurationProperties(prefix = "application")
public class AppProps {
    private Locale locale;
    private int rightAnswerCount;
    private Resource enResource;
    private Resource ruResource;

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public int getRightAnswerCount() {
        return rightAnswerCount;
    }

    public void setRightAnswerCount(int rightAnswerCount) {
        this.rightAnswerCount = rightAnswerCount;
    }

    public Resource getResource() {
        if(locale.getLanguage().equals("en"))
            return enResource;
        else
            return ruResource;
    }

    public void setEnResource(Resource enResource) {
        this.enResource = enResource;
    }

    public void setRuResource(Resource ruResource) {
        this.ruResource = ruResource;
    }
}
