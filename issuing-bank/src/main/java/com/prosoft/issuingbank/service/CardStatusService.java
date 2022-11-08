package com.prosoft.issuingbank.service;

import com.prosoft.issuingbank.model.entity.CardStatus;

import java.util.Optional;

public interface CardStatusService {

    Optional<CardStatus> getCardStatusByCardStatusName(String cardStatusName);

}
