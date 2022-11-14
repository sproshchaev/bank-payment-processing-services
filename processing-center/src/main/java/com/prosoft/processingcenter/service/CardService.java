package com.prosoft.processingcenter.service;

import com.prosoft.processingcenter.model.dto.CardDto;
import com.prosoft.processingcenter.model.dto.Payment;
import com.prosoft.processingcenter.model.entity.Card;

import java.util.Optional;

public interface CardService {
    void getCardFromIssuingBank(CardDto[] cardDtoArray);

    Optional<Card> getCardByCardNumber(String cardNumber);

    Optional<Double> getBalanceByCardNumberAndCurrency(String cardNumber, String currencyLetterCode);

    boolean cardNumberVerified(String cardNumber);

    boolean cardExpired(Card card);

    boolean statusIsValid(Card card);

    String getCardCurrencyLetterCode(Card card);

    double getSumCardCurrency(Card card, Payment payment);

    boolean cardBalanceIsSufficient (Card card, Payment payment);

}
