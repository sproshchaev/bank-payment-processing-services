package com.prosoft.issuingbank.util.shell;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.prosoft.issuingbank.service.ProcessingCenterMessageService;
import org.h2.tools.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.sql.SQLException;


@ShellComponent
public class AppEventsCommands {

    private final ProcessingCenterMessageService processingCenterMessageService;

    @Autowired
    public AppEventsCommands(ProcessingCenterMessageService processingCenterMessageService) {
        this.processingCenterMessageService = processingCenterMessageService;
    }

    @ShellMethod(value = "Start console H2", key = {"c", "console"})
    public void startConsoleH2() {
        try {
            Console.main();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @ShellMethod(value = "Send new cards to processing-center", key = {"sc", "sendcard"})
    public void sendNewCard() throws JsonProcessingException {
        processingCenterMessageService.sendMessage();
    }


}
