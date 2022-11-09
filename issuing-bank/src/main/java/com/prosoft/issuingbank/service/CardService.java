package com.prosoft.issuingbank.service;

import com.prosoft.issuingbank.model.entity.Card;

import java.util.List;

public interface CardService {

    Card createCard(long clientId, long accountId, long paymentSystemId);

    List<Card> getAllCardsByClientId(long clientId);

}
