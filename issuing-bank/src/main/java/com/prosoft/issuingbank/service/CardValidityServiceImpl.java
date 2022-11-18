package com.prosoft.issuingbank.service;

import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class CardValidityServiceImpl implements CardValidityService {

    private final static int CARD_ISSUE_PERIOD_IN_DAYS = 730; // todo срок выпуска карты брать из переменной

    @Override
    public Date getCardExpirationDate() {
        return Date.valueOf(LocalDate.now().plusDays(CARD_ISSUE_PERIOD_IN_DAYS));
    }
}
