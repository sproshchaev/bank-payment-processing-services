package com.prosoft.issuingbank.service;

import com.prosoft.issuingbank.model.entity.Account;
import com.prosoft.issuingbank.model.entity.Transaction;
import com.prosoft.issuingbank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final AccountService accountService;
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(AccountService accountService, TransactionRepository transactionRepository) {
        this.accountService = accountService;
        this.transactionRepository = transactionRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> getAllTransactionsByAccountId(long accountId) {
        Optional<Account> account = accountService.getAccountById(accountId);
        if (account.isPresent()) {
            return transactionRepository.getAllByAccount(account.get());
        } else {
            return null;
        }
    }
}
