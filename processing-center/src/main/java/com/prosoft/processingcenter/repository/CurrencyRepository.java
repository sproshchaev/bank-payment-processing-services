package com.prosoft.processingcenter.repository;


import com.prosoft.processingcenter.model.entity.Currency;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    Optional<Currency> getCurrencyByCurrencyLetterCode(String currencyLetterCode);

}
