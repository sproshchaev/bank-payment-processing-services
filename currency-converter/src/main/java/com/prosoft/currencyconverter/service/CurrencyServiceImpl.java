package com.prosoft.currencyconverter.service;

import com.prosoft.currencyconverter.model.entity.Currency;
import com.prosoft.currencyconverter.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Currency> getCurrencyByCurrencyLetterCode(String currencyLetterCode) {
        return currencyRepository.getCurrencyByCurrencyLetterCode(currencyLetterCode);
    }
}
