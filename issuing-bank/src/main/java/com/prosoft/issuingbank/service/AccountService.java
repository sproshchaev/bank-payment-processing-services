package com.prosoft.issuingbank.service;

import com.prosoft.issuingbank.model.entity.Account;
import com.prosoft.issuingbank.model.entity.Client;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Account createAccount(long clientId, String currencyLetterCode);

    Optional<Account> getAccountById(long accountId);

    Optional<Double> getAccountBalanceById(long accountId);

    List<Account> getAllAccountByClientId(long clientId);

    void updateBalanceFromTransactions(Account account, double balance);

}
