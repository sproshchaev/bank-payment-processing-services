package com.prosoft.processingcenter.service;

import com.prosoft.processingcenter.model.entity.PaymentSystem;

import java.util.Optional;

public interface PaymentSystemService {

    Optional<PaymentSystem> getPaymentSystemByPaymentSystemName(String paymentSystemName);

}
