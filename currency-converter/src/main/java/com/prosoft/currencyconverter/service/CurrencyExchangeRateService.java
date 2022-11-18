package com.prosoft.currencyconverter.service;

import com.prosoft.currencyconverter.model.dto.CurrencyExchangeRate;

public interface CurrencyExchangeRateService {
    CurrencyExchangeRate getCurrencyExchangeRateExt(String currencyLetterCodeFrom, String currencyLetterCodeTo);

}
