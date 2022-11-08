package com.prosoft.issuingbank.service;

import com.prosoft.issuingbank.model.entity.PaymentSystem;
import org.springframework.stereotype.Service;

@Service
public class CardNumGeneratorServiceImpl implements CardNumGeneratorService {

    @Override
    public String getCardNumber(PaymentSystem paymentSystem) {
        return "1234567890123456";
    }
}
