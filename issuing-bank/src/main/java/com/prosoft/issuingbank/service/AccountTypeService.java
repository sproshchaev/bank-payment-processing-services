package com.prosoft.issuingbank.service;

import com.prosoft.issuingbank.model.entity.AccountType;

public interface AccountTypeService {
    AccountType getAccountTypeByName(String accountTypeName);
}
