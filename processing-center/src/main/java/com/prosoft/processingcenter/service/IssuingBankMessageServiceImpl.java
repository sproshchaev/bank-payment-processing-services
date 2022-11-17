package com.prosoft.processingcenter.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prosoft.processingcenter.model.dto.CardDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IssuingBankMessageServiceImpl implements IssuingBankMessageService {

    private final CardService cardService;
    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public IssuingBankMessageServiceImpl(CardService cardService, ObjectMapper objectMapper,
                                         RabbitTemplate rabbitTemplate) {
        this.cardService = cardService;
        this.objectMapper = objectMapper;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public String sendAllMessage() {
        return "Отправлено в Банк "
                + sendCardMessage() + ", "
                + sendTransactionMessage();
    }

    // todo - отправка в Банк информации о картах, которые были загружены в ПЦ и по ним был изменен статус карты
    @Override
    public String sendCardMessage() {
        List<CardDto> newCardList = cardService.getAllCardsByDateSentToIssuingBank(null)
                .stream().map(c -> new CardDto(c.getCardNumber(),
                        c.getExpirationDate(),
                        c.getHolderName(),
                        c.getCardStatus().getCardStatusName(),
                        c.getPaymentSystem().getPaymentSystemName(),
                        c.getAccount().getAccountNumber(),
                        c.getAccount().getBalance(),
                        c.getAccount().getCurrency().getCurrencyLetterCode(),
                        c.getAccount().getIssuingBank().getId()))
                .collect(Collectors.toList());
        if (!newCardList.isEmpty()) {
            try {
                rabbitTemplate.convertAndSend("newCardToIssuingBank", objectMapper.writeValueAsString(newCardList));
                cardService.setDateSentToIssuingBank(new Timestamp(System.currentTimeMillis()),
                        newCardList.stream().map(CardDto::getCardNumber).collect(Collectors.toList()));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return "Cards: " + newCardList.size();
    }

    @Override
    public String sendTransactionMessage() {
        return null;
    }

}
