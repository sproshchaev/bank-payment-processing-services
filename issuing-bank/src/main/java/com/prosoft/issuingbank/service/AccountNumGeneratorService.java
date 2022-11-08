package com.prosoft.issuingbank.service;

import com.prosoft.issuingbank.model.entity.Currency;

public interface AccountNumGeneratorService {
    String getAccountNumber(long clientId, Currency currency);
}
