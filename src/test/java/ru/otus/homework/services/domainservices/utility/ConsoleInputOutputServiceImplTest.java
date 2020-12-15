package ru.otus.homework.services.domainservices.utility;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConsoleInputOutputServiceImplTest {
    @Mock
    private PrintStream out;

    private InputOutputService inputOutputService;
    private final String test = "Test";

    @BeforeEach
    void setUp() {
        InputStream in = new ByteArrayInputStream(test.getBytes());
        inputOutputService = new ConsoleInputOutputServiceImpl(in, out);
    }

    @Test
    void testPrintMethodByTimes() {
        inputOutputService.print(test);

        verify(out, times(1)).println(test);
    }

    @Test
    void testReadMethodByNoSuchElementException() {
        final String actual = inputOutputService.read();

        assertEquals(test, actual);
    }
}
