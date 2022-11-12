package com.prosoft.processingcenter.service;

import com.prosoft.processingcenter.model.dto.Payment;
import org.springframework.stereotype.Service;

// todo IssuerAuthorizationPhase
// Находим карту
// Проверяем срок действия
// Проверяем статус карты
// Проверяем валюту (курс валюты)
// Проверяем сумму на карте
// Генерируем код авторизации
// Генерируем код ответа

@Service
public class IssuerAuthorizationPhase {

    public Payment doServiceB(Payment payment) {
        System.out.println("run IssuerAuthorizationPhase");
        payment.setErrorCode("BB");
        return payment;
    }

}
