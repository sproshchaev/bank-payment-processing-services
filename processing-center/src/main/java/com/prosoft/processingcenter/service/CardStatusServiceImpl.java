package com.prosoft.processingcenter.service;

import com.prosoft.processingcenter.model.entity.CardStatus;
import com.prosoft.processingcenter.repository.CardStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CardStatusServiceImpl implements CardStatusService {
    private final CardStatusRepository cardStatusRepository;

    @Autowired
    public CardStatusServiceImpl(CardStatusRepository cardStatusRepository) {
        this.cardStatusRepository = cardStatusRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CardStatus> getCardStatusByCardStatusName(String cardStatusName) {
        return cardStatusRepository.getCardStatusByCardStatusName(cardStatusName);
    }
}
