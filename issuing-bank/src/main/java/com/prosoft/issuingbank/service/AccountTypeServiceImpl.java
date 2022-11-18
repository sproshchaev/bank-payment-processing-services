package com.prosoft.issuingbank.service;

import com.prosoft.issuingbank.model.entity.AccountType;
import com.prosoft.issuingbank.repository.AccountTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountTypeServiceImpl implements AccountTypeService {

    private final AccountTypeRepository accountTypeRepository;

    @Autowired
    public AccountTypeServiceImpl(AccountTypeRepository accountTypeRepository) {
        this.accountTypeRepository = accountTypeRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public AccountType getAccountTypeByName(String accountTypeName) {
        return accountTypeRepository.findByAccountTypeName(accountTypeName).get();
    }
}
