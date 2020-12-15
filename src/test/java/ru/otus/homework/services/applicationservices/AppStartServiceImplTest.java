package ru.otus.homework.services.applicationservices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.homework.domain.StudentProfile;
import ru.otus.homework.services.domainservices.StudentProfilePrintService;
import ru.otus.homework.services.domainservices.StudentTestService;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AppStartServiceImplTest {
    @MockBean
    private StudentTestService studentTestService;
    @MockBean
    private StudentProfilePrintService studentProfilePrintService;

    @Autowired
    private AppStartService appStartService;
    private StudentProfile studentProfile;

    @BeforeEach
    void setUp(){
        studentProfile = new StudentProfile("firstname", "lastname");
        studentProfile.setPassed(true);
        studentProfile.setFailedQuestions(new ArrayList<>());
        studentProfile.setGivenAnswers(new ArrayList<>());

        when(studentTestService.testStudent()).thenReturn(studentProfile);
        doNothing().when(studentProfilePrintService).printStudentResult(studentProfile);
    }

    @Test
    void start() {
        appStartService.start();

        final InOrder inOrder = inOrder(studentTestService, studentProfilePrintService);
        inOrder.verify(studentTestService).testStudent();
        inOrder.verify(studentProfilePrintService).printStudentResult(studentProfile);
    }
}
