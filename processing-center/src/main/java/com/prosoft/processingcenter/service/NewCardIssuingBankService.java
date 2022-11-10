package com.prosoft.processingcenter.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prosoft.processingcenter.model.dto.Card;
import com.prosoft.processingcenter.model.dto.NewCard;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewCardIssuingBankService {

    private final ObjectMapper objectMapper;

    @Autowired
    public NewCardIssuingBankService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "newCardQueue")
    public void listenNewCardQueue(String in) {
        Card[] newCardArray;
        try {
            newCardArray = objectMapper.readValue(in, Card[].class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Message read from newCardQueue: " + newCardArray[0].toString());
    }

}
