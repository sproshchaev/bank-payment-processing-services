package com.prosoft.issuingbank.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prosoft.issuingbank.model.dto.CardDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// todo Получение прогруженных в ПЦ новых карт для изменения статуса в Банке-эмитенте
@Service
public class CardProcessingCenterServiceImpl implements CardProcessingCenterService {
    private final CardService cardService;
    private final ObjectMapper objectMapper;

    @Autowired
    public CardProcessingCenterServiceImpl(CardService cardService, ObjectMapper objectMapper) {
        this.cardService = cardService;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "newCardToIssuingBank")
    public void listenNewCardQueue(String in) {
        CardDto[] cardDtoDtoArray;
        try {
            cardDtoDtoArray = objectMapper.readValue(in, CardDto[].class);
            cardService.getCardFromProcessingCenter(cardDtoDtoArray);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
