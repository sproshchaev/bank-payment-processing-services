package com.prosoft.processingcenter.service;

import com.prosoft.processingcenter.model.entity.PaymentSystem;
import com.prosoft.processingcenter.repository.PaymentSystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PaymentSystemServiceImpl implements PaymentSystemService {

    private final PaymentSystemRepository paymentSystemRepository;

    @Autowired
    public PaymentSystemServiceImpl(PaymentSystemRepository paymentSystemRepository) {
        this.paymentSystemRepository = paymentSystemRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PaymentSystem> getPaymentSystemByPaymentSystemName(String paymentSystemName) {
        return paymentSystemRepository.getPaymentSystemByPaymentSystemName(paymentSystemName);
    }
}
