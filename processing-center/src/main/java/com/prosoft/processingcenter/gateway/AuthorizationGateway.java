package com.prosoft.processingcenter.gateway;

import com.prosoft.processingcenter.model.dto.PaymentDto;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.stereotype.Component;

@Component
@MessagingGateway
public interface AuthorizationGateway {
    @Gateway(requestChannel = "inputChannel", replyChannel = "outputChannel")
    PaymentDto process(PaymentDto paymentDto);
}
