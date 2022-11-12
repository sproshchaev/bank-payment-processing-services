package com.prosoft.processingcenter.service;

import com.prosoft.processingcenter.model.entity.Account;
import com.prosoft.processingcenter.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Account> getAccountByAccountNumber(String accountNumber) {
        return accountRepository.getAccountByAccountNumber(accountNumber);
    }

    @Override
    @Transactional
    public void updateBalanceFromTransactions(Account account, double balance) {
        account.setBalance(balance);
        accountRepository.save(account);
    }
}
