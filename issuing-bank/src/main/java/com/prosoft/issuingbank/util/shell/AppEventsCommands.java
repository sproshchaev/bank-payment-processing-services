package com.prosoft.issuingbank.util.shell;

import com.prosoft.issuingbank.model.entity.*;
import com.prosoft.issuingbank.service.*;
import org.h2.tools.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@ShellComponent
public class AppEventsCommands {
    private final ClientService clientService;
    private final AccountService accountService;
    private final CardService cardService;
    private final TransactionService transactionService;
    private final ProcessingCenterMessageServiceImpl processingCenterMessageServiceImpl;
    private final AccountNumGeneratorService accountNumGeneratorService;
    private final CardNumGeneratorService cardNumGeneratorService;
    private final CurrencyService currencyService;
    private final PaymentSystemService paymentSystemService;
    private final BankSettingService bankSettingService;

    @Autowired
    public AppEventsCommands(ClientService clientService, AccountService accountService, CardService cardService,
                             TransactionService transactionService,
                             ProcessingCenterMessageServiceImpl processingCenterMessageServiceImpl,
                             AccountNumGeneratorService accountNumGeneratorService,
                             CardNumGeneratorService cardNumGeneratorService, CurrencyService currencyService,
                             PaymentSystemService paymentSystemService, BankSettingService bankSettingService) {
        this.clientService = clientService;
        this.accountService = accountService;
        this.cardService = cardService;
        this.transactionService = transactionService;
        this.processingCenterMessageServiceImpl = processingCenterMessageServiceImpl;
        this.accountNumGeneratorService = accountNumGeneratorService;
        this.cardNumGeneratorService = cardNumGeneratorService;
        this.currencyService = currencyService;
        this.paymentSystemService = paymentSystemService;
        this.bankSettingService = bankSettingService;
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
        return "Клиент создан: " + clientService.createClient(new Client(lastName, firstName, middleName, birthDate,
                document, address, phone, email)).toString();
    }

    @ShellMethod(value = "Create an account", key = {"ca", "createaccount"})
    public String createAccount(@ShellOption(defaultValue = "1", help = "Client's id") long clientId,
                                @ShellOption(defaultValue = "RUB", help = "Currency letter code: RUB, USD, EUR, UAH")
                                String currencyLetterCode) {
        return "Счет открыт " + accountService.createAccount(clientId, currencyLetterCode).getAccountNumber();
    }

    @ShellMethod(value = "Generating an account number", key = {"ga", "genaccount"})
    public String genAccountNumber(@ShellOption(defaultValue = "1", help = "Account's id") long accountId,
                                   @ShellOption(defaultValue = "RUB", help = "Currency letter code: RUB, USD, EUR, UAH")
                                   String currencyLetterCode) {
        Optional<Currency> currencyOptional = currencyService.getCurrencyByCurrencyLetterCode(currencyLetterCode);
        return "Для id=" + accountId + " сгенерирован номер счета: " + accountNumGeneratorService
                .genAccountNumber(accountId, currencyOptional.get());
    }

    @ShellMethod(value = "Create a bank card", key = {"cbc", "createbankcard"})
    public String createBankCard(@ShellOption(defaultValue = "1", help = "Client's id") long clientId,
                                 @ShellOption(defaultValue = "RUB", help = "Currency letter code: RUB, USD, EUR, UAH")
                                 String currencyLetterCode,
                                 @ShellOption(defaultValue = "1", help = "Payment system id") long paymentSystemId) {
        Account account = accountService.createAccount(clientId, currencyLetterCode);
        Card cardCreated = cardService.createCard(clientId, account.getId(), paymentSystemId);
        return "Карта открыта: " + cardCreated.getCardNumber() + " " + cardCreated.getExpirationDate() + " "
                + cardCreated.getHolderName();
    }

    @ShellMethod(value = "Generating a card number", key = {"gc", "generatecard"})
    public String genCardNumber(@ShellOption(defaultValue = "1", help = "Card number id") long cardId,
                                @ShellOption(defaultValue = "1", help = "Payment system id") long paymentSystemId) {
        return "Номер карты сгенерирован: " + cardNumGeneratorService.getCardNumber(cardId,
                paymentSystemService.getPaymentSystemById(paymentSystemId).get(),
                bankSettingService.getBySetting("bin").get().getCurrentValue());
    }

    @ShellMethod(value = "Get all client's accounts", key = {"gaa", "getallaccounts"})
    public String getAllClientAccounts(@ShellOption(defaultValue = "1", help = "Client's id") long clientId) {
        List<Account> accountList = accountService.getAllAccountByClientId(clientId);
        if (accountList == null) {
            return "Счета не найдены!";
        } else {
            return "Счета клиента: \n" + accountList.stream().map((s) -> s.getId() + ") " + s.getAccountNumber())
                    .collect(Collectors.joining(", \n")) + ".";
        }
    }

    @ShellMethod(value = "Get account balance", key = {"gb", "getbalance"})
    public String getAccountBalance(@ShellOption(defaultValue = "1", help = "Account's id") long accountId) {
        Optional<Account> account = accountService.getAccountById(accountId);
        return account.map(value -> "Баланс: " + value.getBalance()).orElse("Счет не найден!");

    }

    // todo n+1 ?
    @ShellMethod(value = "Get all transactions on the account", key = {"gat", "gettransactions"})
    public String getTransactionsByAccountId(@ShellOption(defaultValue = "1", help = "Account's id") long accountId) {
        List<Transaction> transactionList = transactionService.getAllTransactionsByAccountId(accountId);
        if (transactionList == null) {
            return "Транзакции не найдены!";
        } else {
            return "Транзакции: \n" + transactionList.stream().map((t) -> t.getId() + ") " + t.getTransactionDate() + " "
                    + t.getTransactionName() + " " + t.getSum() + " "
                    + t.getAccount().getCurrency().getCurrencyLetterCode()).collect(Collectors.joining(", \n")) + ".";
        }
    }

    @ShellMethod(value = "Get all client's cards", key = {"gac", "getallcards"})
    public String getAllCards(@ShellOption(defaultValue = "1", help = "Client's id") long clientId) {
        List<Card> cardList = cardService.getAllCardsByClientId(clientId);
        if (cardList == null) {
            return "Карты не найдены";
        } else {
            return "Карты клиента: \n" + cardList.stream().map((s) -> s.getId() + ") " + s.getCardNumber())
                    .collect(Collectors.joining(", \n")) + ".";
        }
    }

    @ShellMethod(value = "Create a transaction (Debit/Credit) on the account", key = {"ct", "createtransaction"})
    public String createTransaction(@ShellOption(defaultValue = "1", help = "Account's id") long accountId,
                                    @ShellOption(defaultValue = "2",
                                            help = "Transaction type's id: 1 - Debit, 2 - Credit")
                                    long transactionTypeId,
                                    @ShellOption(defaultValue = "100.28", help = "Account's id") double sum,
                                    @ShellOption(defaultValue = "Пополнение счета", help = "Transaction Comment")
                                    String transactionName) {
        Transaction transaction = transactionService.createTransactionByAccountId(accountId,
                transactionTypeId, sum, transactionName);
        if (transaction == null) {
            return "Транзакция не создана - проверьте параметры!";
        } else {
            return "Транзакция создана: " + transaction.getTransactionDate() + " "
                    + transaction.getAccount().getAccountNumber() + " " + transaction.getSum() + " "
                    + transaction.getAccount().getCurrency().getCurrencyLetterCode() + " "
                    + transaction.getTransactionName();
        }
    }

    @ShellMethod(value = "Send messages (new cards and transactions) to the processing-center", key = {"sm", "sendmessage"})
    public String sendMessageToProcessingCenter() {
        return processingCenterMessageServiceImpl.sendAllMessage();
    }

}
