package com.prosoft.processingcenter.service;

import com.prosoft.processingcenter.model.entity.Account;

import java.util.Optional;

public interface AccountService {

    Optional<Account> getAccountByAccountNumber(String accountNumber);

    void updateBalanceFromTransactions(Account account, double balance);

    boolean accountVerified(Account account);

}
