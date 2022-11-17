package com.prosoft.processingcenter.service;

import com.prosoft.processingcenter.model.dto.PaymentDto;

public interface IssuerAuthorizationPhase {
    PaymentDto checkCardParameters(PaymentDto paymentDto);
}
