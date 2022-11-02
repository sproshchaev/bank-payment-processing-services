package com.prosoft.currencyconverter.service;

import com.prosoft.currencyconverter.model.dto.CurrencyExchangeRate;

public interface CurrencyExchangeRateService {
    CurrencyExchangeRate getCurrencyExchangeRate(String currencyLetterCodeFrom, String currencyLetterCodeTo);
}
