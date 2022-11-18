package com.prosoft.issuingbank.service;

import com.prosoft.issuingbank.model.dto.TransactionDto;
import com.prosoft.issuingbank.model.entity.Account;
import com.prosoft.issuingbank.model.entity.Transaction;
import com.prosoft.issuingbank.model.entity.TransactionType;
import com.prosoft.issuingbank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
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

    @Autowired
    public TransactionServiceImpl(AccountService accountService, TransactionTypeService transactionTypeService, TransactionRepository transactionRepository) {
        this.accountService = accountService;
        this.transactionTypeService = transactionTypeService;
        this.transactionRepository = transactionRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> getAllTransactionsByAccountId(long accountId) {
        Optional<Account> accountOptional = accountService.getAccountById(accountId);
        return accountOptional.map(transactionRepository::getAllByAccount).get();
    }

    @Override
    @Transactional
    public Transaction createTransactionByAccountId(long accountId, long transactionTypeId, double sum,
                                                    String transactionName) {
        Optional<Account> account = accountService.getAccountById(accountId);
        Optional<TransactionType> transactionType = transactionTypeService.getTransactionTypeById(transactionTypeId);
        if (account.isPresent() && transactionType.isPresent()) {
            Transaction transactionCreated =
                    transactionRepository.save(new Transaction(new Date(new java.util.Date().getTime()), sum,
                            transactionName, transactionType.get(), account.get()));
            accountService.updateBalanceFromTransactions(account.get(), getBalanceFromTransactions(account.get()));
            return transactionCreated;
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> getAllTransactionsByDateSentToProcessingCenter(Timestamp sentToProcessingCenter) {
        return transactionRepository.getAllBySentToProcessingCenter(sentToProcessingCenter);
    }

    @Override
    @Transactional
    public void setDateSentToProcessingCenter(Timestamp sentToProcessingCenter, List<Long> transactionIdList) {
        for (Long transactionId : transactionIdList) {
            Optional<Transaction> transaction = transactionRepository.findById(transactionId);
            if (transaction.isPresent()) {
                transaction.get().setSentToProcessingCenter(sentToProcessingCenter);
                transactionRepository.save(transaction.get());
            }
        }
    }

    @Override
    @Transactional
    public void getTransactionFromProcessingCenter(TransactionDto[] transactionDtoArray) {
        System.out.println("\nMessage read from transactionToIssuingBank: \n" + Arrays.stream(transactionDtoArray)
                .map(TransactionDto::toString).collect(Collectors.joining(", \n")));
        Timestamp receivedFromProcessingCenter = new Timestamp(System.currentTimeMillis());
        TransactionType transactionTypeDebet = transactionTypeService.getByTransactionTypeName("Debit").get();
        TransactionType transactionTypeCredit = transactionTypeService.getByTransactionTypeName("Credit").get();
        List<TransactionDto> transactionDtoList = Arrays.stream(transactionDtoArray).collect(Collectors.toList());
        transactionDtoList.forEach(t -> {
            Optional<Account> account = accountService.getAccountByAccountNumber(t.getAccountNumber());
            if (account.isPresent()) {
                transactionRepository.save(new Transaction(
                        t.getTransactionDate(),
                        t.getSum(),
                        t.getTransactionName(),
                        t.getTransactionTypeName().contains("Пополнение счета") ? transactionTypeCredit : transactionTypeDebet,
                        account.get(),
                        receivedFromProcessingCenter));
                accountService.updateBalanceFromTransactions(account.get(), getBalanceFromTransactions(account.get()));
            } else {
                // todo gen. exception
                System.out.println("Reject: Ошибка в параметрах транзакции id=" + t.getIssuingBankIdTransaction() + ": "
                        + "  - transaction=" + account.isPresent() + "\n");
            }
        });
    }

    @Override
    public double getBalanceFromTransactions(Account account) {
        List<Transaction> transactionList = getAllTransactionsByAccountId(account.getId());
        double newBalance = 0;
        if (transactionList != null) {
            for (Transaction transaction : transactionList) {
                if (transaction.getTransactionType().getTransactionTypeName().equals("Debit")) {
                    newBalance = newBalance - transaction.getSum();
                } else {
                    newBalance = newBalance + transaction.getSum();
                }
            }
        }
        return roundDouble(newBalance, 2);
    }

    private double roundDouble(double sum, int digit) {
        double result = sum;
        NumberFormat nf = NumberFormat.getInstance();
        try {
            result = nf.parse(String.format("%." + digit + "f", sum)).doubleValue();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

}
