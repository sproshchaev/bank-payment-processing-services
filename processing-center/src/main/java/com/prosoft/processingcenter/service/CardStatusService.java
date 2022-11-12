package com.prosoft.processingcenter.service;

import com.prosoft.processingcenter.model.entity.CardStatus;

import java.util.Optional;

public interface CardStatusService {

    Optional<CardStatus> getCardStatusByCardStatusName(String cardStatusName);

}
