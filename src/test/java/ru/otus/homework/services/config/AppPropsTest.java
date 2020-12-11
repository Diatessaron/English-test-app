package ru.otus.homework.services.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.homework.Main;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Main.class, AppProps.class})
class AppPropsTest {
    @Value("${application.locale}")
    private Locale locale;
    private final AppProps appProps;

    @Autowired
    public AppPropsTest(AppProps appProps) {
        this.appProps = appProps;
    }

    @Test
    void testLocale() {
        assertEquals(appProps.getLocale(), locale);
    }
}
