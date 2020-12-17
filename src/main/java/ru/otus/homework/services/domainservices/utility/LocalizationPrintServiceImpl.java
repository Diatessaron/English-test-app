package ru.otus.homework.services.domainservices.utility;

import org.springframework.stereotype.Service;

@Service
public class LocalizationPrintServiceImpl implements LocalizationPrintService{
    private final InputOutputService inputOutputService;
    private final LocalizationService localizationService;

    public LocalizationPrintServiceImpl(InputOutputService inputOutputService, LocalizationService localizationService) {
        this.inputOutputService = inputOutputService;
        this.localizationService = localizationService;
    }

    public void printMessage(String str, Object... objects) {
        inputOutputService.print(localizationService.getMessage(str, objects));
    }
}
