package com.prosoft.processingcenter.service;

import com.prosoft.processingcenter.model.dto.PaymentDto;

public interface AcquiringAuthorizationPhase {
    PaymentDto checkTerminalParameters(PaymentDto paymentDto);
}
