package com.prosoft.processingcenter.service;

import com.prosoft.processingcenter.gateway.AuthorizationGateway;
import com.prosoft.processingcenter.logging.LogThis;
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
    @LogThis
    public Payment paymentAuthorization(Payment payment) {
        Payment resultPayment = authorizationGateway.process(payment);
        return resultPayment;
    }
}
