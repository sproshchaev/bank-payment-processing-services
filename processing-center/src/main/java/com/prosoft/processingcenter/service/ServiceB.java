package com.prosoft.processingcenter.service;

import com.prosoft.processingcenter.model.dto.Payment;
import org.springframework.stereotype.Service;

@Service
public class ServiceB {

    public Payment doServiceB(Payment payment) {
        System.out.println("run ServiceB");
        payment.setErrorCode("BB");
        return payment;
    }

}
