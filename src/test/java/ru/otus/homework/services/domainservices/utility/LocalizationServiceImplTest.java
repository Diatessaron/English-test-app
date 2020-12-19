package ru.otus.homework.services.domainservices.utility;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.NoSuchMessageException;
import ru.otus.homework.services.config.AppProps;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class LocalizationServiceImplTest {
    @MockBean
    private AppProps appProps;

    @Autowired
    private LocalizationServiceImpl localizationService;

    @BeforeEach
    void setUp(){
        final Locale locale = new Locale("en");
        when(appProps.getLocale()).thenReturn(locale);
    }

    @Test
    void testByCorrectArguments() {
        final String expected;

        expected = "Hello! Please, enter your first name:";

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
