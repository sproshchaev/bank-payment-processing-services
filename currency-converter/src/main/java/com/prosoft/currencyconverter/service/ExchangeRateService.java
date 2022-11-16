package com.prosoft.currencyconverter.service;

import com.prosoft.currencyconverter.model.entity.ExchangeRate;

import java.util.Optional;

public interface ExchangeRateService {
    void saveExchangeRate(String from, String to, double result);

    Optional<ExchangeRate> getCurrencyExchangeRateInt(String currencyLetterCodeFrom, String currencyLetterCodeTo);

}
