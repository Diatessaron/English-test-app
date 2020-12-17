package ru.otus.homework.services.domainservices.utility;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.homework.services.config.AppProps;

@Service
public class LocalizationServiceImpl implements LocalizationService{
    private final MessageSource messageSource;
    private final AppProps appProps;

    public LocalizationServiceImpl(MessageSource messageSource, AppProps appProps) {
        this.messageSource = messageSource;
        this.appProps = appProps;
    }

    @Override
    public String getMessage(String message, Object... objects) {
        if(message == null || message.isBlank())
            throw new IllegalArgumentException("Message can not be null or blank");
        else if(appProps.getLocale() == null)
            throw new IllegalArgumentException("Locale can not be null");

        return messageSource.getMessage(message, objects, appProps.getLocale());
    }
}
