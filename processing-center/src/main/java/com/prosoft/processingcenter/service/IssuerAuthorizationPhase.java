package com.prosoft.processingcenter.service;

import com.prosoft.processingcenter.model.dto.Payment;

public interface IssuerAuthorizationPhase {
    Payment checkCardParameters(Payment payment);
}
