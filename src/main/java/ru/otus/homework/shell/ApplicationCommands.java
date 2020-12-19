package ru.otus.homework.shell;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.services.applicationservices.AppStartService;

@ShellComponent
public class ApplicationCommands {
    private final AppStartService appStartService;

    private String login;

    public ApplicationCommands(AppStartService appStartService) {
        this.appStartService = appStartService;
    }

    @ShellMethod(key = {"l", "login"}, value = "Login command")
    public String login(@ShellOption("login") String login){
        this.login = login;
        return "You are logged in";
    }

    @ShellMethod(key = {"s", "start"}, value = "Start test")
    @ShellMethodAvailability(value = "isStudentProfileAvailable")
    public void startTesting(){
        appStartService.start();
    }

    private Availability isStudentProfileAvailable() {
        return login == null ? Availability.unavailable("You should be logged in"): Availability.available();
    }
}
