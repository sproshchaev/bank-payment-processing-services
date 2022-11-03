package com.prosoft.salespoint.service;

import com.prosoft.salespoint.feign.AuthorizationServiceProxy;
import com.prosoft.salespoint.model.dto.PaymentValueObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentAuthorizationServiceImpl implements PaymentAuthorizationService {

    private final static String AUTHORIZATION_URL = "http://localhost:8080/authorization/tid/{tid}/date/{date}/card/{card}/expdate/{expdate}/sum/{sum}/curr/{curr}";

    private final RestTemplate restTemplate;

    @Autowired // todo fein через конструктор
    private AuthorizationServiceProxy authorizationServiceProxy;

    @Autowired
    public PaymentAuthorizationServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PaymentValueObject makeAuthorization(PaymentValueObject requestPaymentValueObject) {
        if (false) {
            Map<String, String> urlPathVariables = new HashMap<>();
            urlPathVariables.put("tid", requestPaymentValueObject.getTerminalId());
            urlPathVariables.put("date", requestPaymentValueObject.getTransactionDate());
            urlPathVariables.put("card", requestPaymentValueObject.getCardNumber());
            urlPathVariables.put("expdate", requestPaymentValueObject.getExpiryDate());
            urlPathVariables.put("sum", requestPaymentValueObject.getSum());
            urlPathVariables.put("curr", requestPaymentValueObject.getCurrencyLetterCode());
            ResponseEntity<PaymentValueObject> responseEntity = restTemplate.getForEntity(
                    AUTHORIZATION_URL,
                    PaymentValueObject.class, urlPathVariables);
            PaymentValueObject responsePaymentValueObject = responseEntity.getBody();
            return responsePaymentValueObject;
        } else {
            // feign
            PaymentValueObject responsePaymentValueObject = authorizationServiceProxy.paymentAuthorization(
                    requestPaymentValueObject.getTerminalId(),
                    requestPaymentValueObject.getTransactionDate(),
                    requestPaymentValueObject.getCardNumber(),
                    requestPaymentValueObject.getExpiryDate(),
                    requestPaymentValueObject.getSum(),
                    requestPaymentValueObject.getCurrencyLetterCode()
                    );
            return responsePaymentValueObject;
        }
    }
}
