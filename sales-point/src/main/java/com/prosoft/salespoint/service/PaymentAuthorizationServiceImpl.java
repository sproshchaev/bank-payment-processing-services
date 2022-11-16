package com.prosoft.salespoint.service;

import com.prosoft.salespoint.feign.AuthorizationServiceProxy;
import com.prosoft.salespoint.model.vo.PaymentValueObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentAuthorizationServiceImpl implements PaymentAuthorizationService {
    private final static String AUTHORIZATION_URL = "http://localhost:8080/authorization/tid/{tid}/date/{date}/card/{card}/expdate/{expdate}/sum/{sum}/curr/{curr}";
    private final RestTemplate restTemplate;
    private final AuthorizationServiceProxy authorizationServiceProxy;
    private final boolean useFeign;
    private final TransactionService transactionService;

    @Autowired
    public PaymentAuthorizationServiceImpl(RestTemplate restTemplate, AuthorizationServiceProxy authorizationServiceProxy,
                                           @Value("${pos.request.feign}") boolean useFeign,
                                           TransactionService transactionService) {
        this.restTemplate = restTemplate;
        this.authorizationServiceProxy = authorizationServiceProxy;
        this.useFeign = useFeign;
        this.transactionService = transactionService;
    }

    public PaymentValueObject makeAuthorization(PaymentValueObject requestPaymentValueObject) {
        // todo PaymentValueObject вынести
        if (useFeign) {
            PaymentValueObject responsePaymentValueObject = authorizationServiceProxy.paymentAuthorization(
                    requestPaymentValueObject.getTerminalId(),
                    requestPaymentValueObject.getTransactionDate(),
                    requestPaymentValueObject.getCardNumber(),
                    requestPaymentValueObject.getExpiryDate(),
                    requestPaymentValueObject.getSum(),
                    requestPaymentValueObject.getCurrencyLetterCode());
            transactionService.createPaymentTransaction(responsePaymentValueObject);
            return responsePaymentValueObject;
        } else {
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
        }

    }
}
