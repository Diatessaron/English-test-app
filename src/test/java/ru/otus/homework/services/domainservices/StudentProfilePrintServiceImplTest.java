package ru.otus.homework.services.domainservices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import ru.otus.homework.domain.StudentProfile;
import ru.otus.homework.services.config.AppProps;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class StudentProfilePrintServiceImplTest {
    @Mock
    private InputOutputService inputOutputService;

    private final MessageSource messageSource;
    private final AppProps appProps;
    private StudentProfilePrintServiceImpl studentProfilePrintService;

    @Autowired
    public StudentProfilePrintServiceImplTest(MessageSource messageSource, AppProps appProps) {
        this.messageSource = messageSource;
        this.appProps = appProps;
    }

    @BeforeEach
    void setUp() {
        doNothing().when(inputOutputService).print(any());
        studentProfilePrintService = new StudentProfilePrintServiceImpl(inputOutputService, messageSource, appProps);
    }

    @Test
    void printStudentResult() {
        final StudentProfile studentProfile = new StudentProfile("firstname", "lastname");
        studentProfile.setPassed(true);
        studentProfile.setFailedQuestions(new ArrayList<>());

        studentProfilePrintService.printStudentResult(studentProfile);

        final InOrder inOrder = inOrder(inputOutputService);

        inOrder.verify(inputOutputService).print(anyString());
    }
}
