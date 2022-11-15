package com.prosoft.processingcenter.service;

import com.prosoft.processingcenter.model.dto.CardDto;
import com.prosoft.processingcenter.model.dto.Payment;
import com.prosoft.processingcenter.model.entity.*;
import com.prosoft.processingcenter.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl implements CardService {
    private final static String NEWCARD_CARD_STATUS_NAME = "Card is not active";
    private final PaymentSystemService paymentSystemService;
    private final CardStatusService cardStatusService;
    private final CurrencyService currencyService;
    private final AccountService accountService;
    private final IssuingBankService issuingBankService;
    private final CardRepository cardRepository;

    @Autowired
    public CardServiceImpl(PaymentSystemService paymentSystemService, CardStatusService cardStatusService,
                           CurrencyService currencyService, AccountService accountService,
                           IssuingBankService issuingBankService, CardRepository cardRepository) {
        this.paymentSystemService = paymentSystemService;
        this.cardStatusService = cardStatusService;
        this.currencyService = currencyService;
        this.accountService = accountService;
        this.issuingBankService = issuingBankService;
        this.cardRepository = cardRepository;
    }

    @Override
    @Transactional
    public void getCardFromIssuingBank(CardDto[] cardDtoArray) {
        System.out.println("\nMessage read from newCardQueue: \n" + Arrays.stream(cardDtoArray)
                .map(c -> c.toString()).collect(Collectors.joining(", \n")));
        Timestamp receivedFromIssuingBank = new Timestamp(System.currentTimeMillis());
        Optional<CardStatus> cardStatusIsValid = cardStatusService.getCardStatusByCardStatusName("Card is valid");
        List<CardDto> cardDtoList = Arrays.stream(cardDtoArray).collect(Collectors.toList());
        cardDtoList.forEach(c -> {
            Optional<Card> card = cardRepository.getCardByCardNumber(c.getCardNumber());
            if (card.isPresent()) {
                card.get().setExpirationDate(c.getExpirationDate());
                card.get().setHolderName(c.getHolderName());
                cardRepository.save(card.get());
            } else {
                Optional<PaymentSystem> paymentSystem = paymentSystemService
                        .getPaymentSystemByPaymentSystemName(c.getPaymentSystemName());
                Optional<CardStatus> cardStatus = cardStatusService.getCardStatusByCardStatusName(c.getCardStatusName());
                Optional<Currency> currency = currencyService
                        .getCurrencyByCurrencyLetterCode(c.getCurrencyLetterCode());
                Optional<Account> account = accountService.getAccountByAccountNumber(c.getAccountNumber());
                Optional<IssuingBank> issuingBank = issuingBankService.getIssuingBankById(c.getIssuingBankId());
                if (instanceIsPresent(paymentSystem, cardStatus, currency, issuingBank)) {
                    cardRepository.save(new Card(c.getCardNumber(),
                            c.getExpirationDate(),
                            c.getHolderName(),
                            isNewCardStatusName(c.getCardStatusName()) ? cardStatusIsValid.get() : cardStatus.get(),
                            paymentSystem.get(),
                            account.orElseGet(() -> new Account(c.getAccountNumber(), c.getBalance(), currency.get(),
                                    issuingBank.get())),
                            receivedFromIssuingBank));
                    // todo Отправить сообщение Банку об обработке карты: сохранить номер карты в листе обработанных и через класс CardIssuingBankServiceImpl (или другой отправить эмитенту)
                } else {
                    // todo gen. exception
                    System.out.println("Reject: Ошибка в параметрах карты " + c.getCardNumber() + ": "
                            + "  - paymentSystem=" + paymentSystem.isPresent() + "\n" + "  - cardStatus=" + cardStatus
                            .isPresent() + "\n" + "  - currency=" + currency.isPresent() + "\n" + "  - account="
                            + account.isPresent() + "\n" + "  - issuingBank=" + issuingBank.isPresent() + "\n"
                    );
                }
            }
        });
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Card> getCardByCardNumber(String cardNumber) {
        return cardRepository.getCardByCardNumber(cardNumber);
    }

    @Override
    public Optional<Double> getBalanceByCardNumberAndCurrency(String cardNumber, String currencyLetterCode) {
        return getCardByCardNumber(cardNumber)
                .map(c -> {
                    if (c.getAccount().getCurrency().getCurrencyLetterCode().equals(currencyLetterCode)) {
                        return c.getAccount().getBalance();
                    } else {
                        return currencyService.convertSum(c.getAccount().getBalance(),
                                        c.getAccount().getCurrency().getCurrencyLetterCode(), currencyLetterCode).get();
                    }
                });
    }

    private boolean instanceIsPresent(Optional<PaymentSystem> paymentSystem, Optional<CardStatus> cardStatus,
                                      Optional<Currency> currency, Optional<IssuingBank> issuingBank) {
        return paymentSystem.isPresent() && cardStatus.isPresent() && currency.isPresent() && issuingBank.isPresent();
    }

    private boolean isNewCardStatusName(String cardStatusName) {
        return cardStatusName.equals(NEWCARD_CARD_STATUS_NAME);
    }

    public boolean cardNumberVerified(String cardNumber) {
        // todo включить проверку контрольной цифры карты getLuhnDigit
        return cardNumber.length() == 16;
    }

    public boolean cardExpired(Card card) {
        Date dateNow = new Date(new java.util.Date().getTime());
        Date dateExp = card.getExpirationDate();
        return dateExp.compareTo(dateNow) < 0;
    }

    public boolean statusIsValid(Card card) {
        return card.getCardStatus().getCardStatusName().contains("Card is valid");
    }

    @Override
    public String getCardCurrencyLetterCode(Card card) {
        return card.getAccount().getCurrency().getCurrencyLetterCode();
    }

    @Override
    public double getSumCardCurrency(Card card, Payment payment) {
        if (card.getAccount().getCurrency().getCurrencyLetterCode().equals(payment.getCurrencyLetterCode())) {
            return Double.parseDouble(payment.getSum());
        } else {
            return currencyService.convertSum(Double.parseDouble(payment.getSum()),
                    payment.getCurrencyLetterCode(),
                    card.getAccount().getCurrency().getCurrencyLetterCode()).get();
        }
    }

    @Override
    public boolean cardBalanceIsSufficient (Card card, Payment payment){
        return card.getAccount().getBalance() >= Double.parseDouble(payment.getSumCardCurrency());

    }

    private int getLuhnDigit(String number) {
        int sum = 0;
        int digit;
        for (int i = 0; i < number.length(); i++) {
            digit = Integer.parseInt(number.substring(i, i + 1));
            if (number.length() % 2 != 0) {
                if (i % 2 == 0) {
                    sum = (digit * 2 > 9) ? sum + (digit * 2 - 9) : sum + digit * 2;
                } else {
                    sum = sum + digit;
                }
            } else {
                if (i % 2 != 0) {
                    sum = (digit * 2 > 9) ? sum + (digit * 2 - 9) : sum + digit * 2;
                } else {
                    sum = sum + digit;
                }
            }
        }
        return 10 - sum % 10;
    }

}
