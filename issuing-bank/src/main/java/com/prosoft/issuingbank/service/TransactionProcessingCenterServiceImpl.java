package com.prosoft.issuingbank.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prosoft.issuingbank.model.dto.TransactionDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionProcessingCenterServiceImpl implements TransactionProcessingCenterService {
    private final TransactionService transactionService;
    private final ObjectMapper objectMapper;

    @Autowired
    public TransactionProcessingCenterServiceImpl(TransactionService transactionService, ObjectMapper objectMapper) {
        this.transactionService = transactionService;
        this.objectMapper = objectMapper;
    }

    @Override
    @RabbitListener(queues = "transactionToIssuingBank")
    public void listenTransactionQueue(String in) {
        TransactionDto[] transactionDtoArray;
        try {
            transactionDtoArray = objectMapper.readValue(in, TransactionDto[].class);
            transactionService.getTransactionFromProcessingCenter(transactionDtoArray);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
