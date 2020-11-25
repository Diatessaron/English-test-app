package ru.otus.homework.service;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import ru.otus.homework.domain.Answer;
import ru.otus.homework.domain.Question;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionReaderService {
    private final Resource resource;

    public QuestionReaderService(String pathToCsv) {
        this.resource = new FileSystemResource(pathToCsv);
    }

    public List<Question> getQuestions() {
        List<String> questionStringList = new ArrayList<>();
        List<String> answerStringList = new ArrayList<>();

        getContent(questionStringList, answerStringList);

        return formQuestions(questionStringList, answerStringList);
    }

    private void getContent(List<String> questionStringList, List<String> answerStringList) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            String row;
            int i = 1;

            while ((row = bufferedReader.readLine()) != null) {
                if (i % 2 == 0)
                    answerStringList.add(row);
                else if (i % 2 == 1)
                    questionStringList.add(row);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Question> formQuestions(List<String> questionStringList, List<String> answerStringList) {
        List<Question> questions = new ArrayList<>();

        for (int i = 0; i < questionStringList.size(); i++) {
            String questionContent = questionStringList.get(i);
            List<Answer> answerList = Arrays.stream(answerStringList.get(i).split(";"))
                    .map(Answer::new).collect(Collectors.toList());

            questions.add(new Question(i + 1, questionContent, answerList));
        }

        return questions;
    }
}
