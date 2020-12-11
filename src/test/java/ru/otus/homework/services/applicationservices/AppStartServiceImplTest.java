package ru.otus.homework.services.applicationservices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homework.domain.StudentProfile;
import ru.otus.homework.services.domainservices.StudentProfilePrintService;
import ru.otus.homework.services.domainservices.StudentTestService;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppStartServiceImplTest {
    @Mock
    private StudentTestService studentTestService;

    @Mock
    private StudentProfilePrintService studentProfilePrintService;

    private AppStartService appStartService;

    @BeforeEach
    void setUp(){
        final StudentProfile studentProfile = new StudentProfile("firstname", "lastname");
        studentProfile.setPassed(true);
        studentProfile.setFailedQuestions(new ArrayList<>());
        studentProfile.setGivenAnswers(new ArrayList<>());

        when(studentTestService.testStudent()).thenReturn(studentProfile);
        doNothing().when(studentProfilePrintService).printStudentResult(studentProfile);

        appStartService = new AppStartServiceImpl(studentTestService, studentProfilePrintService);
    }

    @Test
    void start() {
        appStartService.start();

        final InOrder inOrder = inOrder(studentTestService, studentProfilePrintService);
        inOrder.verify(studentTestService).testStudent();
        inOrder.verify(studentProfilePrintService).printStudentResult(any());
    }
}
