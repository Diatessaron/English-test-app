package ru.otus.homework.services.domainservices.utility;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.homework.Main;
import ru.otus.homework.services.config.AppProps;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Main.class)
class LocalizationPrintServiceImplTest {
    @Mock
    private InputOutputService inputOutputService;

    @Autowired
    private MessageSource messageSource;
    @Autowired
    private AppProps appProps;
    private LocalizationService localizationService;
    private LocalizationPrintServiceImpl localizationPrintService;

    @BeforeEach
    void setUp() {
        localizationService = new LocalizationServiceImpl(messageSource, appProps);
        localizationPrintService = new LocalizationPrintServiceImpl(inputOutputService, localizationService);
    }

    @Test
    void testWithCorrectArguments() {
        localizationPrintService.printMessage("firstname");

        verify(inputOutputService, times(1)).print(anyString());
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
}
