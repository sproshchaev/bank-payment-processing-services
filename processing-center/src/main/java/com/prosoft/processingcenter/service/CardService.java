package com.prosoft.processingcenter.service;

import com.prosoft.processingcenter.model.dto.CardDto;
import com.prosoft.processingcenter.model.dto.PaymentDto;
import com.prosoft.processingcenter.model.entity.Card;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface CardService {
    void getCardFromIssuingBank(CardDto[] cardDtoArray);

    Optional<Card> getCardByCardNumber(String cardNumber);

    Optional<Double> getBalanceByCardNumberAndCurrency(String cardNumber, String currencyLetterCode);

    boolean cardNumberVerified(String cardNumber);

    boolean cardExpired(Card card);

    boolean statusIsValid(Card card);

    String getCardCurrencyLetterCode(Card card);

    double getSumCardCurrency(Card card, PaymentDto paymentDto);

    boolean cardBalanceIsSufficient (Card card, PaymentDto paymentDto);

    List<Card> getAllCardsByDateSentToIssuingBank(Timestamp sentToIssuingBank);

    void setDateSentToIssuingBank(Timestamp sentToIssuingBank, List<String> cardNumberList);

}
