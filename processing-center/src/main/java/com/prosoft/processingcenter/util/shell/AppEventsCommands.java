package com.prosoft.processingcenter.util.shell;

import com.prosoft.processingcenter.model.dto.Payment;
import com.prosoft.processingcenter.model.entity.Card;
import com.prosoft.processingcenter.service.AuthorizationService;
import com.prosoft.processingcenter.service.CardService;
import com.prosoft.processingcenter.service.CurrencyService;
import org.h2.tools.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.sql.SQLException;
import java.util.Optional;


@ShellComponent
public class AppEventsCommands {

    private final AuthorizationService authorizationService;

    private final CurrencyService currencyService;
    private final CardService cardService;


    @Autowired
    public AppEventsCommands(AuthorizationService authorizationService, CurrencyService currencyService, CardService cardService) {
        this.authorizationService = authorizationService;
        this.currencyService = currencyService;
        this.cardService = cardService;
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
    public String paymentAuthorizationService(@ShellOption(defaultValue = "000000001", help = "Terminal Id") String tid,
                                              @ShellOption(defaultValue = "2022-10-26", help = "Date operation")
                                              String date,
                                              @ShellOption(defaultValue = "4123450101654724", help = "Card number")
                                              String cardNumber,
                                              @ShellOption(defaultValue = "1225", help = "Exp.date card") String expDate,
                                              @ShellOption(defaultValue = "500.55", help = "Sum") String sum,
                                              @ShellOption(defaultValue = "RUB", help = "Currency") String currency) {
        return authorizationService.paymentAuthorization(new Payment(tid, date, cardNumber, expDate, sum,
                currency)).toString();
    }

    @ShellMethod(value = "Get a course", key = {"gc", "getcourse"})
    public String paymentAuthorizationService(@ShellOption(defaultValue = "USD",
            help = "Currency from (USD, EUR, RUB)") String currencyLetterCodeFrom,
                                              @ShellOption(defaultValue = "RUB",
                                                      help = "Currency to (RUB, USD, EUR)") String currencyLetterCodeTo) {
        return currencyService.getCourse(currencyLetterCodeFrom, currencyLetterCodeTo);
    }

    @ShellMethod(value = "Get a bank card balance", key = {"gcb", "getcardbalance"})
    public String getCardBalance(@ShellOption(defaultValue = "4123450101654724", help = "Card number") String cardNumber) {
        Optional<Card> card = cardService.getCardByCardNumber(cardNumber);
        return card.map(c -> "Card " + cardNumber + " balance: " + c.getAccount().getBalance() + " "
                + c.getAccount().getCurrency().getCurrencyLetterCode()).orElse("Номер карты не найден!");
    }


}
