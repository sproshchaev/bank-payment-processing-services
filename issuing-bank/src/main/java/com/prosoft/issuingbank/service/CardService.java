package com.prosoft.issuingbank.service;

import com.prosoft.issuingbank.model.entity.Card;

public interface CardService {

    Card createCard(long clientId, long accountId, long paymentSystemId);

}
