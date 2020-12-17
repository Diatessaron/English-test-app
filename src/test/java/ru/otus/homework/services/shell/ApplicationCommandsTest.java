package ru.otus.homework.services.shell;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.Shell;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.homework.services.applicationservices.AppStartService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class ApplicationCommandsTest {
    @MockBean
    private AppStartService appStartService;

    @Autowired
    private Shell shell;

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @Test
    void testLoginWithIncorrectParameters(){
        final String actual = shell.evaluate(() -> "login").toString();

        assertEquals("org.springframework.shell.ParameterMissingResolutionException: " +
                "Parameter 'login string' should be specified", actual);
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @Test
    void shouldReturnExpectedGreetingAfterLogin(){
        final String actual = (String) shell.evaluate(() -> "login Login");

        assertThat(actual).isEqualTo("You are logged in");
    }

    @Test
    void testStartTestingMethodByTimes(){
        shell.evaluate(() -> "l Login");
        shell.evaluate(() -> "s");

        verify(appStartService, times(1)).start();
    }
}
