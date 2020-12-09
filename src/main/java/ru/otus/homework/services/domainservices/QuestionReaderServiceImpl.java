package ru.otus.homework.services.domainservices;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.Answer;
import ru.otus.homework.domain.Question;
import ru.otus.homework.exceptions.QuestionReaderException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionReaderServiceImpl implements QuestionReaderService {
    private final Resource resource;

    public QuestionReaderServiceImpl(@Value("${application.testQuestionsWithAnswers}") Resource resource) {
        this.resource = resource;
    }

    @Override
    public List<Question> getQuestions() {
        List<String> questionStringList = new ArrayList<>();
        List<String> answerStringList = new ArrayList<>();

        fillListsWithDataFromCsvResource(questionStringList, answerStringList);

        return formQuestions(questionStringList, answerStringList);
    }

    private void fillListsWithDataFromCsvResource(List<String> questionStringList, List<String> answerStringList) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            String row;
            int i = 1;

            while ((row = bufferedReader.readLine()) != null) {
                if (row.startsWith("(Q" + i + ")"))
                    questionStringList.add(row.substring(4));
                else if (row.startsWith("(A" + i + ")")) {
                    answerStringList.add(row.substring(4));
                    i++;
                } else
                    throw new QuestionReaderException("Incorrect row");
            }
        } catch (IOException e) {
            throw new QuestionReaderException(e.toString(), e);
        }
    }

    private List<Question> formQuestions(List<String> questionStringList, List<String> answerStringList) {
        List<Question> questions = new ArrayList<>();

        for (int i = 0; i < questionStringList.size(); i++) {
            final String[] tempArray = questionStringList.get(i).split("\\(T\\)");

            String questionContent = tempArray[0];
            String correctAnswer = tempArray[1];
            List<Answer> answerList = Arrays.stream(answerStringList.get(i).split(";"))
                    .map(Answer::new).collect(Collectors.toList());

            questions.add(new Question(i + 1, questionContent, answerList, new Answer(correctAnswer)));
        }

        return questions;
    }
}
