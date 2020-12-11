package ru.otus.homework.services.domainservices.utility;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ConsoleInputOutputServiceImplTest {
    @Mock
    private static InputStream in;
    @Mock
    private static PrintStream out;

    private InputOutputService inputOutputService;

    @BeforeEach
    void setUp() {
        inputOutputService = new ConsoleInputOutputServiceImpl(in, out);
    }

    @Test
    void testPrintMethodByTimes() {
        inputOutputService.print("Hello");

        verify(out, times(1)).println("Hello");
    }

    @Test
    void testReadMethodByNoSuchElementException() {
        assertThrows(NoSuchElementException.class, () -> inputOutputService.read());
    }
}
