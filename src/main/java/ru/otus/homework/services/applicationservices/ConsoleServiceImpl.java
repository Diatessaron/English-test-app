package ru.otus.homework.services.applicationservices;

import ru.otus.homework.exceptions.QuestionReaderException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

public class ConsoleServiceImpl implements ConsoleService{
    @Override
    public void print(String str) {
        System.out.println(str);
    }

    @Override
    public Optional<String> read() {
        Optional<String> result;

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            result = Optional.of(reader.readLine());
        } catch (IOException e){
            throw new QuestionReaderException(e.toString(), e);
        }

        return result;
    }
}
