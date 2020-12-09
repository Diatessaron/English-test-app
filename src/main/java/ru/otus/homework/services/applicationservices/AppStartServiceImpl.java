package ru.otus.homework.services.applicationservices;

import org.springframework.stereotype.Service;
import ru.otus.homework.domain.StudentProfile;
import ru.otus.homework.services.domainservices.StudentProfilePrintService;
import ru.otus.homework.services.domainservices.StudentTestService;

@Service
public class AppStartServiceImpl implements AppStartService {
    private final StudentTestService studentTestService;
    private final StudentProfilePrintService studentProfilePrintService;

    public AppStartServiceImpl(StudentTestService studentTestService,
                               StudentProfilePrintService studentProfilePrintService) {
        this.studentTestService = studentTestService;
        this.studentProfilePrintService = studentProfilePrintService;
    }

    @Override
    public void start() {
        final StudentProfile studentProfile = studentTestService.testStudent();
        studentProfilePrintService.printStudentResult(studentProfile);
    }
}
