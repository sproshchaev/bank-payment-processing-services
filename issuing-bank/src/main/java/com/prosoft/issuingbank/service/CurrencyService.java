package com.prosoft.issuingbank.service;

import com.prosoft.issuingbank.model.entity.Currency;

import java.util.Optional;

public interface CurrencyService {
    Optional<Currency> getCurrencyById(long currencyId);

    Optional<Currency> getCurrencyByCurrencyLetterCode(String currencyLetterCode);

}
