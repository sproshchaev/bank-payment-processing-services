package com.prosoft.processingcenter.service;

import com.prosoft.processingcenter.model.dto.PaymentDto;

public interface AuthorizationService {
    PaymentDto paymentAuthorization(PaymentDto paymentDto);
}
