package com.prosoft.issuingbank.service;

import com.prosoft.issuingbank.model.entity.PaymentSystem;
import com.prosoft.issuingbank.repository.PaymentSystemRepository;
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
    public Optional<PaymentSystem> getPaymentSystemById(long paymentSystemId) {
        return paymentSystemRepository.findById(paymentSystemId);
    }
}
