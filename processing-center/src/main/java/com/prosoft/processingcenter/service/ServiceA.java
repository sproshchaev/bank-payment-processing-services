package com.prosoft.processingcenter.service;

import com.prosoft.processingcenter.model.dto.Payment;
import org.springframework.stereotype.Service;

@Service
public class ServiceA {

    public Payment doServiceA(Payment payment) {
        System.out.println("run ServiceA");
        payment.setAuthorizationCode("АААААА");
        return payment;
    }

}
