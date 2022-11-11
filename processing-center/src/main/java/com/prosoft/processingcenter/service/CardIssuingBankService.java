package com.prosoft.processingcenter.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prosoft.processingcenter.model.dto.CardDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardIssuingBankService {

    private final CardService cardService;
    private final ObjectMapper objectMapper;

    @Autowired
    public CardIssuingBankService(CardService cardService, ObjectMapper objectMapper) {
        this.cardService = cardService;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "newCardQueue")
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
