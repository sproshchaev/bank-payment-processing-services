package com.prosoft.processingcenter.service;

import com.prosoft.processingcenter.model.dto.CardDto;

public interface CardService {
    void getCardFromIssuingBank(CardDto[] cardDtoArray);
}
