package com.prosoft.issuingbank.service;

import com.prosoft.issuingbank.model.entity.Account;
import com.prosoft.issuingbank.model.entity.Card;
import com.prosoft.issuingbank.model.entity.Transaction;
import com.prosoft.issuingbank.model.entity.TransactionType;
import com.prosoft.issuingbank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

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
        Optional<Account> account = accountService.getAccountById(accountId);
        return account.map(transactionRepository::getAllByAccount).orElse(null);
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

            List<Transaction> transactionList = getAllTransactionsByAccountId(account.get().getId());
            double newBalance = 0;
            if (transactionList != null) {
                for (Transaction transaction: transactionList) {
                    if (transaction.getTransactionType().getTransactionTypeName().equals("Debit")) {
                        newBalance = newBalance - transaction.getSum();
                    } else {
                        newBalance = newBalance + transaction.getSum();
                    }
                }
            }
            accountService.updateBalanceFromTransactions(account.get(), newBalance);
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
}
