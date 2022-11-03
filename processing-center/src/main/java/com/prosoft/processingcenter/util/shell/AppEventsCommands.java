package com.prosoft.processingcenter.util.shell;

import com.prosoft.processingcenter.model.dto.Payment;
import com.prosoft.processingcenter.service.AuthorizationService;
import com.prosoft.processingcenter.service.CurrencyService;
import org.h2.tools.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.sql.SQLException;


@ShellComponent
public class AppEventsCommands {

    private final AuthorizationService authorizationService;

    private final CurrencyService currencyService;

    @Autowired
    public AppEventsCommands(AuthorizationService authorizationService, CurrencyService currencyService) {
        this.authorizationService = authorizationService;
        this.currencyService = currencyService;
    }

    @ShellMethod(value = "Start console H2", key = {"c", "console"})
    public void startConsoleH2() {
        try {
            Console.main();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @ShellMethod(value = "Make authorization", key = {"ma", "makeauthorization"})
    public String paymentAuthorizationService(@ShellOption(defaultValue = "000000001") String tid,
                                              @ShellOption(defaultValue = "2022-10-26") String date,
                                              @ShellOption(defaultValue = "4123450101654724") String card,
                                              @ShellOption(defaultValue = "1225") String expdate,
                                              @ShellOption(defaultValue = "500.55") String sum,
                                              @ShellOption(defaultValue = "RUB") String curr) {
        return authorizationService.paymentAuthorization(new Payment(tid, date, card, expdate, sum,
                curr)).toString();
    }

    @ShellMethod(value = "Get a course", key = {"gc", "getcourse"})
    public String paymentAuthorizationService(@ShellOption(defaultValue = "USD") String currencyLetterCodeFrom,
                                              @ShellOption(defaultValue = "RUB") String currencyLetterCodeTo) {
        return currencyService.getCourse(currencyLetterCodeFrom, currencyLetterCodeTo).toString();
    }


}
