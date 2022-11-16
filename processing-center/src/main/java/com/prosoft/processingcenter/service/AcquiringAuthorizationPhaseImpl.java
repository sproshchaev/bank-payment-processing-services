package com.prosoft.processingcenter.service;

import com.prosoft.processingcenter.logging.LogThis;
import com.prosoft.processingcenter.model.dto.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcquiringAuthorizationPhaseImpl implements AcquiringAuthorizationPhase{
    private final TerminalService terminalService;

    @Autowired
    public AcquiringAuthorizationPhaseImpl(TerminalService terminalService) {
        this.terminalService = terminalService;
    }

    @LogThis
    @Override
    public Payment checkTerminalParameters(Payment payment) {
        if (terminalService.getByTerminalId(payment.getTerminalId()).isEmpty()) {
            payment.setErrorCode("03");
            payment.setDescription("Invalid merchant or service provider (Недействительный идентификатор продавца)");
        }
        return payment;
    }
}
