package com.prosoft.processingcenter.service;

import com.prosoft.processingcenter.model.entity.Currency;

import java.util.Optional;

public interface CurrencyService {

    String getCourse(String from, String to);

    Optional<Currency> getCurrencyByCurrencyLetterCode(String currencyLetterCode);

}
