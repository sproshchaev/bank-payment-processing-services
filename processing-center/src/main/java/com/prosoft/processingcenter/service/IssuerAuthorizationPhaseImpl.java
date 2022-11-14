package com.prosoft.processingcenter.service;

import com.prosoft.processingcenter.model.dto.Payment;
import com.prosoft.processingcenter.model.entity.Card;
import com.prosoft.processingcenter.model.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Optional;

// todo IssuerAuthorizationPhaseImpl https://wiki.mandarin.io/pages/viewpage.action?pageId=5014410
@Service
public class IssuerAuthorizationPhaseImpl implements IssuerAuthorizationPhase {
    private final static String  ERROR_CODE_00 = "00;Approved (Успешная транзакция)";
    private final static String  ERROR_CODE_14 = "14;Invalid card (no such number) (Эмитент указывает, что эта карта недействительна)";
    private final static String  ERROR_CODE_51 = "51;Not sufficient funds (Недостаточно средств на карте)";
    private final static String  ERROR_CODE_54 = "54;Expired card (Срок действия карты истек)";
    private final static String  ERROR_CODE_56 = "56;No card record (Нет такой карты)";
    private final static String  ERROR_CODE_76 = "76;Invalid \"to\" account (Неверный счет. Дебетового счета не существует";
    private final static String  ERROR_CODE_96 = "96;System malfunction  (Произошла системная ошибка)";
    private final CardService cardService;
    private final TransactionService transactionService;
    private final AccountService accountService;

    @Autowired
    public IssuerAuthorizationPhaseImpl(CardService cardService, TransactionService transactionService,
                                        AccountService accountService) {
        this.cardService = cardService;
        this.transactionService = transactionService;
        this.accountService = accountService;
    }

    @Override
    @Transactional //(readOnly = true) (!)
    public Payment checkCardParameters(Payment payment) {
        if (errCodeIsNull(payment)) {
            System.out.println("run IssuerAuthorizationPhaseImpl");
            var card = new Card();
            // todo Проверка номера карты "по луне"
            if (!cardService.cardNumberVerified(payment.getCardNumber())) {
                paymentSetErrorCode(payment, ERROR_CODE_14);
            }
            // Проверка наличия номера карты в БД
            if (errCodeIsNull(payment)) {
                Optional<Card> cardOptional = cardService.getCardByCardNumber(payment.getCardNumber());
                if (cardOptional.isPresent()) {
                    card = cardOptional.get();
                } else {
                    paymentSetErrorCode(payment, ERROR_CODE_56);
                }
            }
            // Проверяем срок действия
            if (errCodeIsNull(payment) && cardService.cardExpired(card)) {
                paymentSetErrorCode(payment, ERROR_CODE_54);
            }
            // Проверяем статус карты
            if (errCodeIsNull(payment) && !cardService.statusIsValid(card)) {
                paymentSetErrorCode(payment,String.join(";", getErrorCodeForStatusCard(card)));
            }
            // Проверка счета:
            if (errCodeIsNull(payment)) {
                if (accountService.accountVerified(card.getAccount())) {
                    setCardSumAndCardCurrencyInPayment(card, payment);
                } else {
                    paymentSetErrorCode(payment, ERROR_CODE_76);
                }
            }
            // Проверка баланса
            if (errCodeIsNull(payment)) {
                if (cardService.cardBalanceIsSufficient(card, payment)) {
                    Optional<Transaction> transactionOptional = transactionService.createTransaction(card, payment,
                            genAuthorizationCode());
                    if (transactionOptional.isPresent()) {
                        paymentSetErrorCode(payment, ERROR_CODE_00);
                        payment.setAuthorizationCode(transactionOptional.get().getAuthorizationCode());
                    } else {
                        paymentSetErrorCode(payment, ERROR_CODE_96);
                    }
                } else {
                    paymentSetErrorCode(payment, ERROR_CODE_51);
                }
            }
        }
        return payment;
    }

    private boolean errCodeIsNull(Payment payment) {
        return payment.getErrorCode() == null;
    }

    private String[] getErrorCodeForStatusCard(Card card) {
        switch (card.getCardStatus().getCardStatusName()) {
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

    private String genAuthorizationCode() {
        return new SimpleDateFormat("SSSssSSS").format(new java.util.Date()).substring(0, 5)
                + (int) (Math.random() * 10);
    }

    private void paymentSetErrorCode(Payment payment, String errorCode) {
        String[] errorCodeArray = errorCode.split(";");
        payment.setErrorCode(errorCodeArray[0]);
        payment.setDescription(errorCodeArray[1]);
    }

    private void setCardSumAndCardCurrencyInPayment(Card card, Payment payment) {
        payment.setCardCurrencyLetterCode(cardService.getCardCurrencyLetterCode(card));
        payment.setSumCardCurrency(String.valueOf(cardService.getSumCardCurrency(card, payment)));
    }

}
