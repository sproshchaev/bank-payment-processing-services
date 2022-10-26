package com.prosoft.processingcenter.gateway;

import com.prosoft.processingcenter.model.dto.Payment;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.stereotype.Component;

@Component
@MessagingGateway
public interface AuthorizationGateway {
    @Gateway(requestChannel = "inputChannel", replyChannel = "outputChannel")
    Payment process(Payment payment);
}
