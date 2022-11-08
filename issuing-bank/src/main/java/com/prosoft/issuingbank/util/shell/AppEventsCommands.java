package com.prosoft.issuingbank.util.shell;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.prosoft.issuingbank.model.entity.Account;
import com.prosoft.issuingbank.model.entity.Client;
import com.prosoft.issuingbank.model.entity.Transaction;
import com.prosoft.issuingbank.service.*;
import org.h2.tools.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@ShellComponent
public class AppEventsCommands {
    private final ClientService clientService;
    private final AccountService accountService;
    private final CardService cardService;
    private final TransactionService transactionService;
    private final ProcessingCenterMessageService processingCenterMessageService;

    @Autowired
    public AppEventsCommands(ClientService clientService, AccountService accountService, CardService cardService, TransactionService transactionService, ProcessingCenterMessageService processingCenterMessageService) {
        this.clientService = clientService;
        this.accountService = accountService;
        this.cardService = cardService;
        this.transactionService = transactionService;
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

    @ShellMethod(value = "Create a new client", key = {"cc", "createclient"})
    public String createClient(@ShellOption(defaultValue = "Семенов", help = "Last name") String lastName,
                               @ShellOption(defaultValue = "Аркадий", help = "First name") String firstName,
                               @ShellOption(defaultValue = "Платонович", help = "Middle name") String middleName,
                               @ShellOption(defaultValue = "1972-10-01", help = "Birth date") Date birthDate,
                               @ShellOption(defaultValue = "1011 456789 выдан 19.05.2015 УВД г.Владивостока",
                                       help = "Document") String document,
                               @ShellOption(defaultValue = "107140, г. Москва, ул.Верхняя Красносельская, 16 кв.32",
                                       help = "Address") String address,
                               @ShellOption(defaultValue = "+79031234570", help = "Phone") String phone,
                               @ShellOption(defaultValue = "pronats@inbox.ru", help = "Email") String email) {
        return "Клиент создан: " + clientService.createClient(new Client(lastName, firstName, middleName, birthDate, document, address,
                phone, email)).toString();
    }

    @ShellMethod(value = "Create an account", key = {"ca", "createaccount"})
    public String createAccount(@ShellOption(defaultValue = "1", help = "Client's id") long clientId,
                                @ShellOption(defaultValue = "RUB", help = "Currency letter code: RUB, USD, EUR, UAH")
                                String currencyLetterCode) {
        return "Счет открыт " + accountService.createAccount(clientId, currencyLetterCode).getAccountNumber();
    }

    @ShellMethod(value = "Create a bank card", key = {"cbc", "createbankcard"})
    public String createBankCard(@ShellOption(defaultValue = "1", help = "Client's id") long clientId,
                                 @ShellOption(defaultValue = "1", help = "Account's id") long accountId,
                                 @ShellOption(defaultValue = "1", help = "Payment system id") long paymentSystemId) {
        return "Карта открыта " + cardService.createCard(clientId, accountId, paymentSystemId).getCardNumber();
    }

    @ShellMethod(value = "Get all client's accounts", key = {"gaa", "getallaccounts"})
    public String getAllClientAccounts(@ShellOption(defaultValue = "1", help = "Client's id") long clientId) {
        List<Account> accountList = accountService.getAllAccountByClientId(clientId);
        if (accountList == null) {
            return "Счета не найдены!";
        } else {
            // todo вывести построчно с id и номером счета
            return "Счета клиента: "
                    + Arrays.toString(accountList.stream().map(Account::getAccountNumber).toArray());
        }
    }

    @ShellMethod(value = "Get account balance", key = {"gb", "getbalance"})
    public String getAccountBalance(@ShellOption(defaultValue = "1", help = "Account's id") long accountId) {
        Optional<Account> account = accountService.getAccountById(accountId);
        return account.map(value -> "Баланс: " + value.getBalance()).orElse("Счет не найден!");

    }

    // todo n+1 ?
    @ShellMethod(value = "Get all transactions on the account", key = {"gt", "gettransactions"})
    public String getTransactionsByAccountId(@ShellOption(defaultValue = "1", help = "Account's id") long accountId) {
        List<Transaction> transactionList = transactionService.getAllTransactionsByAccountId(accountId);
        if (transactionList == null) {
            return "Транзакции не найдены!";
        } else {
            String transactions = "";
            for (int i = 0; i < transactionList.size(); i++) {
                transactions = transactions
                        + (i + 1) + ") "
                        + transactionList.get(i).getTransactionDate() + " "
                        + transactionList.get(i).getTransactionName() + " "
                        + transactionList.get(i).getSum() + " "
                        + transactionList.get(i).getAccount().getCurrency().getCurrencyLetterCode() + "\n";
            }
            return transactions;
        }
    }

    // todo Получить все карты клиента

    // todo Внести на счет карты (наличными)

    // todo Списать со счета карты средства

    // todo Отправить почту в ПЦ
    @ShellMethod(value = "Send new cards to processing-center", key = {"sc", "sendcard"})
    public void sendNewCard() throws JsonProcessingException {
        processingCenterMessageService.sendMessage();
    }

    // todo Получить почту из ПЦ? / Либо ПЦ отправляет инфо об операциях?

}
