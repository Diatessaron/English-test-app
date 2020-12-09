package ru.otus.homework.services.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "/configuration.yml")
class AppPropsTest {
    @Value("${application.locale}")
    private Locale locale;

    @Test
    void testLocale() {
        assertEquals(locale, new Locale("en"));
    }
}
