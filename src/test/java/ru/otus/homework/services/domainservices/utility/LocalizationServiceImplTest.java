package ru.otus.homework.services.domainservices.utility;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.homework.Main;
import ru.otus.homework.services.config.AppProps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Main.class)
class LocalizationServiceImplTest {
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private AppProps appProps;
    private LocalizationServiceImpl localizationService;

    @BeforeEach
    void setUp(){
        localizationService = new LocalizationServiceImpl(messageSource, appProps);
    }

    @Test
    void testByCorrectArguments() {
        final String expected;

        if(appProps.getLocale().getLanguage().equals("en"))
            expected = "Hello! Please, enter your first name:";
        else
            expected = "Здравствуйте! Пожалуйста, введите Ваше имя:";

        final String actual = localizationService.getMessage("firstname");

        assertEquals(expected, actual);
    }

    @Test
    void shouldThrowExceptionBecauseOfNullString(){
        assertThrows(IllegalArgumentException.class, ()
                -> localizationService.getMessage(null));
    }

    @Test
    void shouldThrowExceptionBecauseOfBlankString(){
        assertThrows(IllegalArgumentException.class, ()
                -> localizationService.getMessage(""));
    }

    @Test
    void shouldThrowExceptionBecauseOfIncorrectMessage(){
        assertThrows(NoSuchMessageException.class, ()
                -> localizationService.getMessage("null"));
    }
}
