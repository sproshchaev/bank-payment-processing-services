package com.prosoft.issuingbank.service;

import com.prosoft.issuingbank.model.entity.PaymentSystem;
import org.springframework.stereotype.Service;

// todo bin, lun
@Service
public class CardNumGeneratorServiceImpl implements CardNumGeneratorService {

    private final static int CARD_NUMBER_LENGTH = 16;
    private final static String FILLING_CARD_NUMBER = "0000000000000000";

    @Override
    public String getCardNumber(long cardId, PaymentSystem paymentSystem) {
        int remainderNumber = CARD_NUMBER_LENGTH - paymentSystem.getFirstDigitBin().length()
                - String.valueOf(cardId).length();
        return paymentSystem.getFirstDigitBin() + FILLING_CARD_NUMBER.substring(0, remainderNumber) + cardId;
    }
}
