package com.prosoft.issuingbank.service;

import com.prosoft.issuingbank.model.entity.CardStatus;
import com.prosoft.issuingbank.repository.CardStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardStatusServiceImpl implements CardStatusService {

    private final CardStatusRepository cardStatusRepository;

    @Autowired
    public CardStatusServiceImpl(CardStatusRepository cardStatusRepository) {
        this.cardStatusRepository = cardStatusRepository;
    }

    @Override
    public Optional<CardStatus> getCardStatusByCardStatusName(String cardStatusName) {
        return cardStatusRepository.findByCardStatusName(cardStatusName);
    }
}
