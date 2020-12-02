package ru.otus.homework.services.applicationservices;

import org.springframework.stereotype.Service;
import ru.otus.homework.services.domainservices.StudentTestService;

@Service
public class AppStartServiceImpl implements AppStartService {
    private final StudentTestService studentTestService;

    public AppStartServiceImpl(StudentTestService studentTestService) {
        this.studentTestService = studentTestService;
    }

    @Override
    public void start() {
        studentTestService.testStudent();
    }
}
