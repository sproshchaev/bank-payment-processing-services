package com.prosoft.processingcenter.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prosoft.processingcenter.model.dto.CardDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardIssuingBankServiceImpl implements CardIssuingBankService {
    private final CardService cardService;
    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public CardIssuingBankServiceImpl(CardService cardService, ObjectMapper objectMapper, RabbitTemplate rabbitTemplate) {
        this.cardService = cardService;
        this.objectMapper = objectMapper;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    @RabbitListener(queues = "newCardToProcessingCenter")
    public void listenNewCardQueue(String in) {
        CardDto[] cardDtoArray;
        try {
            cardDtoArray = objectMapper.readValue(in, CardDto[].class);
            cardService.getCardFromIssuingBank(cardDtoArray);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
