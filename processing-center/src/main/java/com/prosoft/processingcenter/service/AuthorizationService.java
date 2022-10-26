package com.prosoft.processingcenter.service;

import com.prosoft.processingcenter.model.dto.Payment;

public interface AuthorizationService {
    Payment paymentAuthorization(Payment payment);
}
