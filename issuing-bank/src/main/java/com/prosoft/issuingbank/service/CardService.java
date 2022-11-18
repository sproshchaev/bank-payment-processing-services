package com.prosoft.issuingbank.service;

import com.prosoft.issuingbank.model.dto.CardDto;
import com.prosoft.issuingbank.model.entity.Card;

import java.sql.Timestamp;
import java.util.List;

public interface CardService {

    Card createCard(long clientId, long accountId, long paymentSystemId);

    List<Card> getAllCardsByClientId(long clientId);

    List<Card> getAllCardsByStatusId(long statusId);

    List<Card> getAllCardsByDateSentToProcessingCenter(Timestamp sentToProcessingCenter);

    void setDateSentToProcessingCenter(Timestamp sentToProcessingCenter, List<String> cardNumberList);

    void getCardFromProcessingCenter(CardDto[] cardDtoArray);

}
