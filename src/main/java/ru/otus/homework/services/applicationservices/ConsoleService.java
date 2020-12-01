package ru.otus.homework.services.applicationservices;

import java.util.Optional;

public interface ConsoleService {
    void print(String str);

    Optional<String> read();
}
