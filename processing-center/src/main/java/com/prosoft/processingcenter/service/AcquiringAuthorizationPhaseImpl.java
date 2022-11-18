package com.prosoft.processingcenter.service;

import com.prosoft.processingcenter.logging.LogThis;
import com.prosoft.processingcenter.model.dto.PaymentDto;
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
    public PaymentDto checkTerminalParameters(PaymentDto paymentDto) {
        if (terminalService.getByTerminalId(paymentDto.getTerminalId()).isEmpty()) {
            paymentDto.setErrorCode("03");
            paymentDto.setDescription("Invalid merchant or service provider (Недействительный идентификатор продавца)");
        }
        return paymentDto;
    }
}
