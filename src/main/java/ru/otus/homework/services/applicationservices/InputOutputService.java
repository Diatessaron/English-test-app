package ru.otus.homework.services.applicationservices;

public interface InputOutputService {
    void print(String str);

    void print(String str, Object ... objects);

    String read();
}
