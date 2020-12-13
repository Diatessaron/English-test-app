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

    private InputStream in;
    private InputOutputService inputOutputService;

    @BeforeEach
    void setUp() {
        in = new ByteArrayInputStream("Test".getBytes());
        inputOutputService = new ConsoleInputOutputServiceImpl(in, out);
    }

    @Test
    void testPrintMethodByTimes() {
        inputOutputService.print("Test");

        verify(out, times(1)).println("Test");
    }

    @Test
    void testReadMethodByNoSuchElementException() {
        final String expected = "Test";
        final String actual = inputOutputService.read();

        assertEquals(expected, actual);
    }
}
