package ru.otus.homework.services.domainservices.utility;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class LocalizationPrintServiceImplTest {
    @MockBean
    private InputOutputService inputOutputService;
    @MockBean
    private LocalizationService localizationService;

    @Autowired
    private LocalizationPrintServiceImpl localizationPrintService;

    @BeforeEach
    void setUp() {
        when(localizationService.getMessage(null)).thenThrow(IllegalArgumentException.class);
        when(localizationService.getMessage("")).thenThrow(IllegalArgumentException.class);
        when(localizationService.getMessage("firstname")).thenReturn("firstnameTest");
    }

    @Test
    void testWithCorrectArguments() {
        localizationPrintService.printMessage("firstname");

        verify(inputOutputService, times(1)).print("firstnameTest");
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
