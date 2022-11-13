package com.prosoft.processingcenter.service;

import com.prosoft.processingcenter.model.dto.Payment;
import com.prosoft.processingcenter.model.dto.TransactionDto;
import com.prosoft.processingcenter.model.entity.Account;
import com.prosoft.processingcenter.model.entity.Card;
import com.prosoft.processingcenter.model.entity.Transaction;
import com.prosoft.processingcenter.model.entity.TransactionType;
import com.prosoft.processingcenter.repository.TerminalRepository;
import com.prosoft.processingcenter.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final AccountService accountService;
    private final TransactionTypeService transactionTypeService;
    private final TransactionRepository transactionRepository;
    private final TerminalRepository terminalRepository;
    private final ResponseCodeService responseCodeService;

    @Autowired
    public TransactionServiceImpl(AccountService accountService, TransactionTypeService transactionTypeService, TransactionRepository transactionRepository, TerminalRepository terminalRepository, ResponseCodeService responseCodeService) {
        this.accountService = accountService;
        this.transactionTypeService = transactionTypeService;
        this.transactionRepository = transactionRepository;
        this.terminalRepository = terminalRepository;
        this.responseCodeService = responseCodeService;
    }

    @Override
    @Transactional
    public void getTransactionFromIssuingBank(TransactionDto[] transactionDtoArray) {
        System.out.println("\nMessage read from transactionQueue: \n" + Arrays.stream(transactionDtoArray)
                .map(t -> t.toString()).collect(Collectors.joining(", \n")));
        Timestamp receivedFromIssuingBank = new Timestamp(System.currentTimeMillis());
        TransactionType transactionTypeDebet = transactionTypeService.getByTransactionTypeName("Списание со счета").get();
        TransactionType transactionTypeCredit = transactionTypeService.getByTransactionTypeName("Пополнение счета").get();
        List<TransactionDto> transactionDtoList = Arrays.stream(transactionDtoArray).collect(Collectors.toList());
        transactionDtoList.forEach(t -> {
            Optional<Account> account = accountService.getAccountByAccountNumber(t.getAccountNumber());
            if (account.isPresent()) {
                transactionRepository.save(new Transaction(
                        t.getTransactionDate(),
                        t.getSum(),
                        t.getTransactionName(),
                        account.get(),
                        t.getTransactionTypeName().contains("Credit") ? transactionTypeCredit : transactionTypeDebet,
                        receivedFromIssuingBank));
                accountService.updateBalanceFromTransactions(account.get(), getBalanceFromTransactions(account.get()));
            } else {
                // todo gen. exception
                System.out.println("Reject: Ошибка в параметрах транзакции id=" + t.getIssuingBankIdTransaction() + ": "
                        + "  - transaction=" + account.isPresent() + "\n");
            }
        });
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> getAllTransactionsByAccountId(Account account) {
        return transactionRepository.getTransactionByAccount(account);
    }

    @Override
    public double getBalanceFromTransactions(Account account) {
/*
        double result = getAllTransactionsByAccountId(account).stream()
                .mapToDouble(t -> {
                    return t.getSum() * (t.getTransactionType().getOperator().equals("-") ? -1 : 1);
                })
                .sum();
        NumberFormat nf = NumberFormat.getInstance();
        try {
            result = nf.parse(String.format("%.2f", result)).doubleValue();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
*/

        return roundDouble(getAllTransactionsByAccountId(account).stream()
                .mapToDouble(t -> {
                    return t.getSum() * (t.getTransactionType().getOperator().equals("-") ? -1 : 1);
                })
                .sum(), 2);
    }

    private double roundDouble(double sum, int digit) {
        double result = sum;
        NumberFormat nf = NumberFormat.getInstance();
        try {
            result = nf.parse(String.format("%."+ digit +"f", sum)).doubleValue();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    //@Transactional (!)
    public Optional<Transaction> createTransaction(Card card, Payment payment, String authorizationCode) {
        TransactionType transactionTypeDebet = transactionTypeService.getByTransactionTypeName("Списание со счета").get();
        Transaction transaction = transactionRepository.save(new Transaction(Date.valueOf(payment.getTransactionDate()),
                Double.parseDouble(payment.getSumCardCurrency()),
                "Purchase payment TID " + payment.getTerminalId(),
                card.getAccount(),
                transactionTypeDebet,
                card,
                terminalRepository.getByTerminalId(payment.getTerminalId()).get(),
                responseCodeService.getByErrorCode("00").get(),
                authorizationCode));
        accountService.updateBalanceFromTransactions(card.getAccount(),
                getBalanceFromTransactions(card.getAccount()));
        return Optional.of(transaction);
    }
}
