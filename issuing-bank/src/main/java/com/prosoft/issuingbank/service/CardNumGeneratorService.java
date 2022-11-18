package com.prosoft.issuingbank.service;

import com.prosoft.issuingbank.model.entity.PaymentSystem;

public interface CardNumGeneratorService {
    String getCardNumber(long cardId, PaymentSystem paymentSystem, String bin);
}
