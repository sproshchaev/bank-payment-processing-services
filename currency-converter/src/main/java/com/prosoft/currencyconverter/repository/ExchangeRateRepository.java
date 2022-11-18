package com.prosoft.currencyconverter.repository;

import com.prosoft.currencyconverter.model.entity.Currency;
import com.prosoft.currencyconverter.model.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

    Optional<ExchangeRate> findTopByCurrencyFromAndCurrencyToOrderByIdDesc(Currency from, Currency to);

}
