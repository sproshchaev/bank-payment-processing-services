package com.prosoft.issuingbank.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prosoft.issuingbank.model.dto.CardDto;
import com.prosoft.issuingbank.model.dto.TransactionCard;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

// todo + интерфейс

@Service
public class ProcessingCenterMessageService {
    private final static long ISSUING_BANK_ID = 1;
    private final CardService cardService;
    private final TransactionService transactionService;
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public ProcessingCenterMessageService(CardService cardService, TransactionService transactionService,
                                          RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.cardService = cardService;
        this.transactionService = transactionService;
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    public String sendAllMessage() {
        return "Отправлено в ПЦ "
                + sendCardMessage() + ", "
                + sendTransactionMessage();
    }

    public String sendCardMessage() {
        List<CardDto> newCardListDto = cardService.getAllCardsByDateSentToProcessingCenter(null)
                .stream().map(c -> new CardDto(c.getCardNumber(),
                        c.getExpirationDate(),
                        c.getHolderName(),
                        c.getCardStatus().getCardStatusName(),
                        c.getPaymentSystem().getPaymentSystemName(),
                        c.getAccount().getAccountNumber(),
                        c.getAccount().getBalance(),
                        c.getAccount().getCurrency().getCurrencyLetterCode(),
                        ISSUING_BANK_ID))
                .collect(Collectors.toList());
        if (!newCardListDto.isEmpty()) {
            try {
                rabbitTemplate.convertAndSend("newCardToProcessingCenter", objectMapper.writeValueAsString(newCardListDto));
                cardService.setDateSentToProcessingCenter(new Timestamp(System.currentTimeMillis()),
                        newCardListDto.stream().map(CardDto::getCardNumber).collect(Collectors.toList()));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return "Cards: " + newCardListDto.size();
    }

    public String sendTransactionMessage() {
        List<TransactionCard> transactionCardList = transactionService
                .getAllTransactionsByDateSentToProcessingCenter(null)
                .stream()
                .map(t -> new TransactionCard(
                        t.getTransactionDate(),
                        t.getSum(),
                        t.getTransactionName(),
                        t.getTransactionType().getTransactionTypeName(),
                        t.getAccount().getAccountNumber(),
                        t.getId()))
                .collect(Collectors.toList());
        if (!transactionCardList.isEmpty()) {
            try {
                rabbitTemplate.convertAndSend("transactionQueue",
                        objectMapper.writeValueAsString(transactionCardList));
                transactionService.setDateSentToProcessingCenter(new Timestamp(System.currentTimeMillis()),
                        transactionCardList.stream().map(TransactionCard::getIssuingBankIdTransaction)
                                .collect(Collectors.toList()));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return "Transactions: " + transactionCardList.size();
    }


}
