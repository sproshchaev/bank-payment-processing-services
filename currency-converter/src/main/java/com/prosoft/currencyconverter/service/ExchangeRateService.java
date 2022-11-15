package com.prosoft.currencyconverter.service;

public interface ExchangeRateService {
    void saveExchangeRate(String from, String to, double result);
}
