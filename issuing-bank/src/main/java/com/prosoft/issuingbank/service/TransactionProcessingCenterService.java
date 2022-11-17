package com.prosoft.issuingbank.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// todo + интерфейс
@Service
public class TransactionProcessingCenterService {
    private final ObjectMapper objectMapper;

    @Autowired
    public TransactionProcessingCenterService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

}
