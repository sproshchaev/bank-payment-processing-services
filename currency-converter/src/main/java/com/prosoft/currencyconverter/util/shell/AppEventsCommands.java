package com.prosoft.currencyconverter.util.shell;

import com.prosoft.currencyconverter.service.CurrencyExchangeRateService;
import org.h2.tools.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.sql.SQLException;


@ShellComponent
public class AppEventsCommands {

    private final CurrencyExchangeRateService currencyExchangeRateService;

    @Autowired
    public AppEventsCommands(CurrencyExchangeRateService currencyExchangeRateService) {
        this.currencyExchangeRateService = currencyExchangeRateService;
    }

    @ShellMethod(value = "Start console H2", key = {"c", "console"})
    public void startConsoleH2() {
        try {
            Console.main();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @ShellMethod(value = "Get a course", key = {"gc", "getcourse"})
    public String paymentAuthorizationService(@ShellOption(defaultValue = "USD") String currencyLetterCodeFrom,
                                              @ShellOption(defaultValue = "RUB") String currencyLetterCodeTo) {
        return currencyExchangeRateService.getCurrencyExchangeRateExt(currencyLetterCodeFrom, currencyLetterCodeTo)
                .toString();
    }

}
