package com.prosoft.processingcenter.service;

import com.prosoft.processingcenter.model.dto.Payment;

public interface AcquiringAuthorizationPhase {
    Payment checkTerminalParameters(Payment payment);
}
