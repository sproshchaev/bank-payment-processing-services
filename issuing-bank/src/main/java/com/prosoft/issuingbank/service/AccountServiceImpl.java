package com.prosoft.issuingbank.service;

import com.prosoft.issuingbank.model.entity.Account;
import com.prosoft.issuingbank.model.entity.Client;
import com.prosoft.issuingbank.model.entity.Currency;
import com.prosoft.issuingbank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final ClientService clientService;
    private final AccountNumGeneratorService accountNumGeneratorService;
    private final CurrencyService currencyService;
    private final AccountTypeService accountTypeService;
    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(ClientService clientService, AccountNumGeneratorService accountNumGeneratorService,
                              CurrencyService currencyService, AccountTypeService accountTypeService,
                              AccountRepository accountRepository) {
        this.clientService = clientService;
        this.accountNumGeneratorService = accountNumGeneratorService;
        this.currencyService = currencyService;
        this.accountTypeService = accountTypeService;
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional // todo внедрить ентити графы!
    public Account createAccount(long clientId, String currencyLetterCode) {
        Optional<Client> client = clientService.getClientById(clientId);
        Optional<Currency> currency = currencyService.getCurrencyByCurrencyLetterCode(currencyLetterCode);
        if (client.isPresent() && currency.isPresent()) {
            Account account = accountRepository.save(new Account(
                    0,
                    currency.get(),
                    accountTypeService.getAccountTypeByName("Passive account"),
                    client.get(),
                    new Date(new java.util.Date().getTime()),
                    false));
            account.setAccountNumber(accountNumGeneratorService.genAccountNumber(account.getId(), account.getCurrency()));
            accountRepository.save(account);
            return account;
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Account> getAccountById(long accountId) {
        return accountRepository.findById(accountId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Double> getAccountBalanceById(long accountId) {
        return Optional.of(accountRepository.findById(accountId).get().getBalance());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Account> getAllAccountByClientId(long clientId) {
        Optional<Client> client = clientService.getClientById(clientId);
        return client.map(accountRepository::getAllByClient).get();
    }

    @Override
    @Transactional
    public void updateBalanceFromTransactions(Account account, double balance) {
        account.setBalance(balance);
        accountRepository.save(account);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Account> getAccountByAccountNumber(String accountNumber) {
        return accountRepository.getAccountByAccountNumber(accountNumber);
    }
}
