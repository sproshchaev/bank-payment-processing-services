package com.prosoft.processingcenter.service;

import com.prosoft.processingcenter.logging.LogThis;
import com.prosoft.processingcenter.model.dto.PaymentDto;
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

    @LogThis
    @Override
    @Transactional //(readOnly = true) (!)
    public PaymentDto checkCardParameters(PaymentDto paymentDto) {
        if (errCodeIsNull(paymentDto)) {
            var card = new Card();
            // todo Проверка номера карты "по луне"
            if (!cardService.cardNumberVerified(paymentDto.getCardNumber())) {
                paymentSetErrorCode(paymentDto, ERROR_CODE_14);
            }
            // Проверка наличия номера карты в БД
            if (errCodeIsNull(paymentDto)) {
                Optional<Card> cardOptional = cardService.getCardByCardNumber(paymentDto.getCardNumber());
                if (cardOptional.isPresent()) {
                    card = cardOptional.get();
                } else {
                    paymentSetErrorCode(paymentDto, ERROR_CODE_56);
                }
            }
            // Проверяем срок действия
            if (errCodeIsNull(paymentDto) && cardService.cardExpired(card)) {
                paymentSetErrorCode(paymentDto, ERROR_CODE_54);
            }
            // Проверяем статус карты
            if (errCodeIsNull(paymentDto) && !cardService.statusIsValid(card)) {
                paymentSetErrorCode(paymentDto,String.join(";", getErrorCodeForStatusCard(card)));
            }
            // Проверка счета:
            if (errCodeIsNull(paymentDto)) {
                if (accountService.accountVerified(card.getAccount())) {
                    setCardSumAndCardCurrencyInPayment(card, paymentDto);
                } else {
                    paymentSetErrorCode(paymentDto, ERROR_CODE_76);
                }
            }
            // Проверка баланса
            if (errCodeIsNull(paymentDto)) {
                if (cardService.cardBalanceIsSufficient(card, paymentDto)) {
                    Optional<Transaction> transactionOptional = transactionService.createTransaction(card, paymentDto,
                            genAuthorizationCode());
                    if (transactionOptional.isPresent()) {
                        paymentSetErrorCode(paymentDto, ERROR_CODE_00);
                        paymentDto.setAuthorizationCode(transactionOptional.get().getAuthorizationCode());
                    } else {
                        paymentSetErrorCode(paymentDto, ERROR_CODE_96);
                    }
                } else {
                    paymentSetErrorCode(paymentDto, ERROR_CODE_51);
                }
            }
        }
        return paymentDto;
    }

    private boolean errCodeIsNull(PaymentDto paymentDto) {
        return paymentDto.getErrorCode() == null;
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

    private void paymentSetErrorCode(PaymentDto paymentDto, String errorCode) {
        String[] errorCodeArray = errorCode.split(";");
        paymentDto.setErrorCode(errorCodeArray[0]);
        paymentDto.setDescription(errorCodeArray[1]);
    }

    private void setCardSumAndCardCurrencyInPayment(Card card, PaymentDto paymentDto) {
        paymentDto.setCardCurrencyLetterCode(cardService.getCardCurrencyLetterCode(card));
        paymentDto.setSumCardCurrency(String.valueOf(cardService.getSumCardCurrency(card, paymentDto)));
    }

}
