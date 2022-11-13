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
        System.out.println("Пришел запрос на авторизацию " + payment.toString()); // todo del
        Payment resultPayment = authorizationGateway.process(payment);
        System.out.println("Фазы авторизации завершены");
        return resultPayment;
    }
}
