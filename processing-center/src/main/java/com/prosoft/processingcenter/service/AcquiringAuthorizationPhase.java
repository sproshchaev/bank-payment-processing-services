package com.prosoft.processingcenter.service;

import com.prosoft.processingcenter.model.dto.Payment;
import org.springframework.stereotype.Service;

// todo: AcquiringAuthorizationPhase - проверка параметров точки sales_point, terminal

// Проверяем тоговую точку (должна быть зарегистрирована)
// Проверяем терминал (должен быть зарегистрирован)

@Service
public class AcquiringAuthorizationPhase {

    public Payment doServiceA(Payment payment) {
        System.out.println("run AcquiringAuthorizationPhase");
        payment.setAuthorizationCode("АААААА");
        return payment;
    }

}
