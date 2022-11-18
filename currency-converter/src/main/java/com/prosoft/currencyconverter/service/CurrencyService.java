package com.prosoft.currencyconverter.service;

import com.prosoft.currencyconverter.model.entity.Currency;

import java.util.Optional;

public interface CurrencyService {
    Optional<Currency> getCurrencyByCurrencyLetterCode(String currencyLetterCode);

}
