package com.prosoft.issuingbank.service;

import com.prosoft.issuingbank.model.entity.PaymentSystem;
import org.springframework.stereotype.Service;

@Service
public class CardNumGeneratorServiceImpl implements CardNumGeneratorService {
    private final static int CARD_NUMBER_LENGTH = 16;
    private final static String FILLING_CARD_NUMBER = "0000000000000000";

    @Override
    public String getCardNumber(long cardId, PaymentSystem paymentSystem, String bin) {
        int remainderNumber = CARD_NUMBER_LENGTH - paymentSystem.getFirstDigitBin().length() - bin.length()
                - String.valueOf(cardId).length() - 1;
        String firstPartCardNumber = paymentSystem.getFirstDigitBin() + bin
                + FILLING_CARD_NUMBER.substring(0, remainderNumber) + cardId;
        int luhnDigit = getLuhnDigit(firstPartCardNumber);
        return firstPartCardNumber + luhnDigit;
    }

    public int getLuhnDigit(String number) {
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
        return sum % 10 == 0 ? 0 : 10 - sum % 10;
    }

}
