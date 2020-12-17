package ru.otus.homework.services.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.homework.Main;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Main.class})
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
