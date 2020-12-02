package ru.otus.homework.services.applicationservices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework.exceptions.ConsoleServiceException;

import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ConsoleServiceImplTest {
    @Mock
    private static InputStream in;
    @Mock
    private static PrintStream out;

    private ConsoleService consoleService;

    @BeforeEach
    void setUp(){
        consoleService = new ConsoleServiceImpl(in, out);
    }

    @Test
    void testPrintMethodByTimes() {
        consoleService.print("Hello");

        verify(out, times(1)).println("Hello");
    }

    //mock InputStream will throw IOException because of zero bytes
    @Test
    void testReadMethodByIOException(){
        assertThrows(ConsoleServiceException.class, () -> consoleService.read());
    }
}
