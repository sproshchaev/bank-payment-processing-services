package com.prosoft.issuingbank.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prosoft.issuingbank.model.dto.NewCard;
import com.prosoft.issuingbank.model.dto.Transaction;
import com.prosoft.issuingbank.model.entity.Card;
import com.prosoft.issuingbank.model.entity.TransactionType;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProcessingCenterMessageService {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public ProcessingCenterMessageService(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendMessage() throws JsonProcessingException {
        ArrayList<NewCard> newCardList = new ArrayList<>();
        newCardList.add(new NewCard("1234567890123456", "IVAN I. IVANOV"));
        newCardList.add(new NewCard("1234567890123457", "PETR P. PETROV"));
        newCardList.add(new NewCard("1234567890123458", "SEMEN S. SEMYONOV"));

        rabbitTemplate.convertAndSend("newCardQueue", objectMapper.writeValueAsString(newCardList));


        ArrayList<Transaction> transactionList = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            transactionList.add(new Transaction("2022-10-28", (i * 100 + 0.1 * i), "Пополнение карты",
                    new TransactionType(1, "Credit"),
                    "4123450101654724"));
        }
        rabbitTemplate.convertAndSend("transactionQueue", objectMapper.writeValueAsString(transactionList));
    }

}
