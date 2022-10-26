package com.prosoft.processingcenter.service;

import com.prosoft.processingcenter.gateway.AuthorizationGateway;
import com.prosoft.processingcenter.model.dto.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    private final AuthorizationGateway authorizationGateway;

    @Autowired
    public AuthorizationServiceImpl(AuthorizationGateway authorizationGateway) {
        this.authorizationGateway = authorizationGateway;
    }

    @Override
    public Payment paymentAuthorization(Payment payment) {

        Payment resultPayment = authorizationGateway.process(payment);

        // Проверяем терминал (должен быть зарегистрирован)
        // Находим карту
        // Проверяем срок действия
        // Проверяем статус карты
        // Проверяем валюту (курс валюты)
        // Проверяем сумму на карте
        // Генерируем код авторизации
        // Генерируем код ответа
        return resultPayment;
    }
}
