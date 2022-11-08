package com.prosoft.issuingbank.service;

import com.prosoft.issuingbank.model.entity.PaymentSystem;

import java.util.Optional;

public interface PaymentSystemService {

    Optional<PaymentSystem> getPaymentSystemById(long paymentSystemId);

}
