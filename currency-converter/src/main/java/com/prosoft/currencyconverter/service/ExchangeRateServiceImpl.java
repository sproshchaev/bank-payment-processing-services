package com.prosoft.currencyconverter.service;

import com.prosoft.currencyconverter.model.entity.Currency;
import com.prosoft.currencyconverter.model.entity.ExchangeRate;
import com.prosoft.currencyconverter.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {
    private final CurrencyService currencyService;

    private final ExchangeRateRepository exchangeRateRepository;

    @Autowired
    public ExchangeRateServiceImpl(CurrencyService currencyService, ExchangeRateRepository exchangeRateRepository) {
        this.currencyService = currencyService;
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @Override
    @Transactional
    public void saveExchangeRate(String from, String to, double result) {
        Optional<Currency> currencyFromOptional = currencyService.getCurrencyByCurrencyLetterCode(from);
        Optional<Currency> currencyToOptional = currencyService.getCurrencyByCurrencyLetterCode(to);
        if (currencyFromOptional.isPresent() && currencyToOptional.isPresent()) {
            exchangeRateRepository.save(new ExchangeRate(new Timestamp(System.currentTimeMillis()),
                    currencyFromOptional.get(),
                    currencyToOptional.get(),
                    result));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ExchangeRate> getCurrencyExchangeRateInt(String currencyLetterCodeFrom, String currencyLetterCodeTo) {
        Optional<Currency> currencyFromOptional = currencyService.getCurrencyByCurrencyLetterCode(currencyLetterCodeFrom);
        Optional<Currency> currencyToOptional = currencyService.getCurrencyByCurrencyLetterCode(currencyLetterCodeTo);
        return exchangeRateRepository.findTopByCurrencyFromAndCurrencyToOrderByIdDesc(currencyFromOptional.get(),
                currencyToOptional.get());
    }

}
