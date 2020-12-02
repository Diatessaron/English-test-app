package ru.otus.homework.services.applicationservices;

import ru.otus.homework.exceptions.ConsoleServiceException;

import java.io.*;

public class ConsoleServiceImpl implements ConsoleService{
    private final BufferedReader reader;
    private final InputStream in;
    private final PrintStream out;

    public ConsoleServiceImpl(InputStream in, PrintStream out) {
        this.in = in;
        this.out = out;
        reader = new BufferedReader(new InputStreamReader(this.in));
    }

    @Override
    public void print(String str) {
        out.println(str);
    }

    @Override
    public String read() {
        String result;

        try{
            result = reader.readLine();
        } catch (IOException e){
            throw new ConsoleServiceException(e.toString(), e);
        }

        return result;
    }
}
