package com.prosoft.processingcenter.service;

import com.prosoft.processingcenter.model.dto.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// todo: AcquiringAuthorizationPhaseImpl https://wiki.mandarin.io/pages/viewpage.action?pageId=5014410
@Service
public class AcquiringAuthorizationPhaseImpl implements AcquiringAuthorizationPhase{
    private final TerminalService terminalService;

    @Autowired
    public AcquiringAuthorizationPhaseImpl(TerminalService terminalService) {
        this.terminalService = terminalService;
    }

    @Override
    public Payment checkTerminalParameters(Payment payment) {
        System.out.println("run AcquiringAuthorizationPhaseImpl");
        if (terminalService.getByTerminalId(payment.getTerminalId()).isEmpty()) {
            payment.setErrorCode("03");
            payment.setDescription("Invalid merchant or service provider (Недействительный идентификатор продавца)");
        }
        return payment;
    }
}
