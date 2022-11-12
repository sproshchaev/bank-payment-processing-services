package com.prosoft.processingcenter.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prosoft.processingcenter.model.dto.TransactionDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class TransactionIssuingBankServiceImpl implements TransactionIssuingBankService {

    private final TransactionService transactionService;
    private final ObjectMapper objectMapper;

    @Autowired
    public TransactionIssuingBankServiceImpl(TransactionService transactionService, ObjectMapper objectMapper) {
        this.transactionService = transactionService;
        this.objectMapper = objectMapper;
    }

    @Override
    @RabbitListener(queues = "transactionQueue")
    public void listenTransactionQueue(String in) {
        TransactionDto[] transactionDtoArray;
        try {
            transactionDtoArray = objectMapper.readValue(in, TransactionDto[].class);
            transactionService.getTransactionFromIssuingBank(transactionDtoArray);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
