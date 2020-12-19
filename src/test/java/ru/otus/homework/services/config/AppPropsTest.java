package ru.otus.homework.services.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AppPropsTest {
    @Value("${application.locale}")
    private Locale locale;
    @Autowired
    private AppProps appProps;

    @Test
    void testLocale() {
        assertEquals(appProps.getLocale(), locale);
    }

    @Test
    void testResource(@Value("${application.resource}") String template){
        Resource classPathResource = new ClassPathResource
                (String.format(template, locale.getLanguage()));

        assertEquals(classPathResource, appProps.getResource());
    }
}
