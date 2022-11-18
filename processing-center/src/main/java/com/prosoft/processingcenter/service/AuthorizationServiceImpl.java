package com.prosoft.processingcenter.service;

import com.prosoft.processingcenter.gateway.AuthorizationGateway;
import com.prosoft.processingcenter.logging.LogThis;
import com.prosoft.processingcenter.model.dto.PaymentDto;
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
    public PaymentDto paymentAuthorization(PaymentDto paymentDto) {
        PaymentDto resultPaymentDto = authorizationGateway.process(paymentDto);
        return resultPaymentDto;
    }
}
