package com.prosoft.processingcenter.service;

import com.prosoft.processingcenter.model.dto.Payment;
import com.prosoft.processingcenter.model.entity.Card;
import com.prosoft.processingcenter.model.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Optional;

// todo IssuerAuthorizationPhaseImpl https://wiki.mandarin.io/pages/viewpage.action?pageId=5014410
@Service
public class IssuerAuthorizationPhaseImpl implements IssuerAuthorizationPhase {
    private final CardService cardService;
    private final CurrencyService currencyService;
    private final TransactionService transactionService;
    private final AccountService accountService;

    @Autowired
    public IssuerAuthorizationPhaseImpl(CardService cardService, CurrencyService currencyService, TransactionService transactionService, AccountService accountService) {
        this.cardService = cardService;
        this.currencyService = currencyService;
        this.transactionService = transactionService;
        this.accountService = accountService;
    }

    @Override
    @Transactional//(readOnly = true)
    public Payment checkCardParameters(Payment payment) {
        if (errCodeIsNull(payment)) {
            System.out.println("run IssuerAuthorizationPhaseImpl");
            Optional<Card> card = cardService.getCardByCardNumber(payment.getCardNumber());
            // Проверка номера карты
            if (card.isEmpty()) {
                payment.setErrorCode("56");
                payment.setDescription("No card record (Нет такой карты)");
            }
            // Проверка счета
            if (errCodeIsNull(payment)) {
                if (accountExist(card)) {
                    payment.setCardCurrencyLetterCode(card.get().getAccount().getCurrency().getCurrencyLetterCode());
                } else {
                    payment.setErrorCode("76");
                    payment.setDescription("Invalid \"to\" account (Неверный счет. Дебетового счета не существует");
                }
            }
            // Проверяем срок действия
            if (errCodeIsNull(payment) && cardExpired(card)) {
                payment.setErrorCode("54");
                payment.setDescription("Expired card (Срок действия карты истек)");
            }
            // Проверяем статус карты
            if (errCodeIsNull(payment) && !statusIsValid(card)) {
                payment.setErrorCode(getErrorCodeForStatusCard(card)[0]);
                payment.setDescription(getErrorCodeForStatusCard(card)[1]);
            }
            // Конвертируем сумму покупки в валюту карты
            if (errCodeIsNull(payment)) {
                if (payment.getCurrencyLetterCode().equals(payment.getCardCurrencyLetterCode())) {
                    payment.setSumCardCurrency(payment.getSum());
                } else {
                        payment.setSumCardCurrency(String.valueOf(currencyService.convertSum(Double.parseDouble(payment
                                        .getSum()), payment.getCurrencyLetterCode(),
                                payment.getCardCurrencyLetterCode()).get()));
                }
            }
            // Проверка баланса
            if (errCodeIsNull(payment)) {
                if (cardBalanceIsSufficient(card, payment)) {
                    Optional<Transaction> transaction = transactionService.createTransaction(card.get(), payment,
                            genAuthorizationCode());
                    payment.setErrorCode("00");
                    payment.setDescription("Approved (Успешная транзакция)");
                    payment.setAuthorizationCode(transaction.get().getAuthorizationCode());
                } else {
                    payment.setErrorCode("51");
                    payment.setDescription("Not sufficient funds (Недостаточно средств на карте)");
                }
            }
        }
        return payment;
    }

    private boolean errCodeIsNull(Payment payment) {
        return payment.getErrorCode() == null;
    }

    private boolean accountExist(Optional<Card> card) {
        return card.get().getAccount() != null;
    }

    private boolean cardExpired(Optional<Card> card) {
        Date dateNow = new Date(new java.util.Date().getTime());
        Date dateExp = card.get().getExpirationDate();
        return dateExp.compareTo(dateNow) < 0;
    }

    private boolean statusIsValid(Optional<Card> card) {
        return card.get().getCardStatus().getCardStatusName().contains("Card is valid");
    }

    private String[] getErrorCodeForStatusCard(Optional<Card> card) {
        switch (card.get().getCardStatus().getCardStatusName()) {
            case "Card is temporarily blocked":
                return new String[]{"62", "Restricted card (Карта заблокирована)"};
            case "Card is lost":
                return new String[]{"41", "Pickup card (lost card) (Карта была утеряна)"};
            case "Card is compromised":
                return new String[]{"43", "Pickup card (stolen card) (Карта была украдена)"};
            default:
                return new String[]{"??", "Not defined"};
        }
    }

    private boolean cardBalanceIsSufficient(Optional<Card> card, Payment payment) {
        return card.get().getAccount().getBalance() >= Double.parseDouble(payment.getSumCardCurrency());
    }

    private String genAuthorizationCode() {
        return new SimpleDateFormat("SSSssSSS").format(new java.util.Date()).substring(0, 5)
                + (int) (Math.random() * 10);
    }
}
