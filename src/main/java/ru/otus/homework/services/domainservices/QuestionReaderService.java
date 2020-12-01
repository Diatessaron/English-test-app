package ru.otus.homework.services.domainservices;

import ru.otus.homework.domain.Question;

import java.util.List;

public interface QuestionReaderService {
    List<Question> getQuestions();
}
