package com.prosoft.processingcenter.util.shell;

import com.prosoft.processingcenter.model.dto.PaymentDto;
import com.prosoft.processingcenter.model.entity.Card;
import com.prosoft.processingcenter.service.*;
import org.h2.tools.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.sql.SQLException;
import java.util.Optional;
import java.util.stream.Collectors;


@ShellComponent
public class AppEventsCommands {
    private final AuthorizationService authorizationService;
    private final CurrencyService currencyService;
    private final CardService cardService;
    private final IssuingBankMessageService issuingBankMessageService;
    private final TransactionService transactionService;
    @Autowired
    public AppEventsCommands(AuthorizationService authorizationService, CurrencyService currencyService,
                             CardService cardService, IssuingBankMessageService issuingBankMessageService,
                             TransactionService transactionService) {
        this.authorizationService = authorizationService;
        this.currencyService = currencyService;
        this.cardService = cardService;

        this.issuingBankMessageService = issuingBankMessageService;
        this.transactionService = transactionService;
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
                                              @ShellOption(defaultValue = "4123450000000019", help = "Card number")
                                              String cardNumber,
                                              @ShellOption(defaultValue = "1225", help = "Exp.date card") String expDate,
                                              @ShellOption(defaultValue = "500.55", help = "Sum") String sum,
                                              @ShellOption(defaultValue = "RUB", help = "Currency") String currency) {
        return authorizationService.paymentAuthorization(new PaymentDto(tid, date, cardNumber, expDate, sum,
                currency)).toString();
    }

    @ShellMethod(value = "Get a course", key = {"gc", "getcourse"})
    public String paymentAuthorizationService(@ShellOption(defaultValue = "USD",
            help = "Currency from (USD, EUR, RUB)") String currencyLetterCodeFrom,
                                              @ShellOption(defaultValue = "RUB",
                                                      help = "Currency to (RUB, USD, EUR)") String currencyLetterCodeTo) {
        return "Курс " + currencyLetterCodeFrom + " " + currencyLetterCodeTo + ": "
                + currencyService.getCourse(currencyLetterCodeFrom, currencyLetterCodeTo).map(Object::toString)
                .orElse(" не найден!");
    }

    @ShellMethod(value = "Get a bank card balance", key = {"gcb", "getcardbalance"})
    public String getCardBalance(@ShellOption(defaultValue = "4123450000000019", help = "Card number") String cardNumber) {
        Optional<Card> card = cardService.getCardByCardNumber(cardNumber);
        return card.map(c -> "Card " + cardNumber + " balance: " + c.getAccount().getBalance() + " "
                + c.getAccount().getCurrency().getCurrencyLetterCode()).orElse("Номер карты не найден!");
    }

    @ShellMethod(value = "Get a bank card balance in currency", key = {"gcbc", "getcardbalancecurr"})
    public String getCardBalanceInCurrency(@ShellOption(defaultValue = "4123450000000019", help = "Card number")
                                           String cardNumber,
                                           @ShellOption(defaultValue = "USD", help = "Currency to (RUB, USD, EUR)")
                                           String currencyLetterCode) {
        Optional<Double> balance = cardService.getBalanceByCardNumberAndCurrency(cardNumber, currencyLetterCode);
        return balance.map(b -> "Card " + cardNumber + " balance: " + b + " " + currencyLetterCode)
                .orElse("Номер карты не найден!");
    }

    @ShellMethod(value = "Get a bank card status", key = {"gcs", "getcardstatus"})
    public String getCardStatus(@ShellOption(defaultValue = "4123450000000019", help = "Card number") String cardNumber) {
        Optional<Card> card = cardService.getCardByCardNumber(cardNumber);
        return card.map(c -> "Card " + cardNumber + " status: (" + c.getCardStatus().getId() + ") "
                + c.getCardStatus().getCardStatusName() + " ").orElse("Номер карты не найден!");
    }

    @ShellMethod(value = "Send messages (new cards and transactions) to the issuing-bank", key = {"sm", "sendmessage"})
    public String sendMessageToIssuingBank() {
        return issuingBankMessageService.sendAllMessage();
    }

    @ShellMethod(value = "Get all payment transactions on the card", key = {"gapt", "getallpaymenttransactions"})
    public String getAllPaymentTransactions(@ShellOption(defaultValue = "4123450000000019", help = "Card number") String cardNumber) {
        Optional<Card> card = cardService.getCardByCardNumber(cardNumber);
        return "Платежные транзакции:\n" + transactionService.getAllPaymentTransactionsByCard(card.get()).stream()
                .map(t -> t.getId() + ") "
                        + t.getTransactionDate() + " "
                        + t.getTransactionName() + " "
                        + t.getSum() + " "
                        + t.getAccount().getCurrency().getCurrencyLetterCode() + " "
                ).collect(Collectors.joining("\n"));
    }

    @ShellMethod(value = "Get all transactions on the card", key = {"gat", "getalltransactions"})
    public String getAllTransactions(@ShellOption(defaultValue = "4123450000000019", help = "Card number") String cardNumber) {
        Optional<Card> card = cardService.getCardByCardNumber(cardNumber);
        return "Транзакции по карте " + cardNumber + ":\n" + transactionService.getAllTransactionsByAccount(card.get().getAccount()).stream()
                .map(t -> t.getId() + ") "
                        + t.getTransactionDate() + " "
                        + t.getTransactionName() + " "
                        + t.getSum() + " "
                        + t.getAccount().getCurrency().getCurrencyLetterCode() + " "
                ).collect(Collectors.joining("\n"));
    }

}
