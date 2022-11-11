package com.prosoft.issuingbank.service;

import com.prosoft.issuingbank.model.entity.Currency;
import org.springframework.stereotype.Service;

@Service
public class AccountNumGeneratorServiceImpl implements AccountNumGeneratorService {

    @Override
    public String getAccountNumber(long clientId, Currency currency) {
        return "1234567890123456789" + clientId; // todo генератор номеров счетов
    }
}
