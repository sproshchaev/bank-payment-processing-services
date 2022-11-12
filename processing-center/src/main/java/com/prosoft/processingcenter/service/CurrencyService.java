package com.prosoft.processingcenter.service;

import com.prosoft.processingcenter.model.entity.Currency;

import java.util.Optional;

public interface CurrencyService {

    Optional<Double> getCourse(String from, String to);

    Optional<Currency> getCurrencyByCurrencyLetterCode(String currencyLetterCode);

    Optional<Double> convertSum(double sum, String fromCurrencyLetterCode, String toCurrencyLetterCode);

}
