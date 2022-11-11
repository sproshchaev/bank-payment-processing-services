package com.prosoft.processingcenter.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prosoft.processingcenter.model.dto.TransactionDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionIssuingBankService {

    private final ObjectMapper objectMapper;

    @Autowired
    public TransactionIssuingBankService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "transactionQueue")
    public void listenTransactionQueue(String in) {
        TransactionDto[] transactionArray;
        try {
            transactionArray = objectMapper.readValue(in, TransactionDto[].class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Message read from transactionQueue: " + transactionArray[0].toString());
    }

}
