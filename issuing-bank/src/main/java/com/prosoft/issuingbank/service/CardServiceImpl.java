package com.prosoft.issuingbank.service;

import com.prosoft.issuingbank.model.entity.*;
import com.prosoft.issuingbank.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {
    private final ClientService clientService;
    private final AccountService accountService;
    private final PaymentSystemService paymentSystemService;
    private final CardStatusService cardStatusService;
    private final CardRepository cardRepository;
    private final CardNumGeneratorService cardNumGeneratorService;
    private final TransliterationService transliterationService;
    private final CardValidityService cardValidityService;

    @Autowired
    public CardServiceImpl(ClientService clientService, AccountService accountService,
                           PaymentSystemService paymentSystemService, CardStatusService cardStatusService, CardRepository cardRepository,
                           CardNumGeneratorService cardNumGeneratorService, TransliterationService transliterationService, CardValidityService cardValidityService) {
        this.clientService = clientService;
        this.accountService = accountService;
        this.paymentSystemService = paymentSystemService;
        this.cardStatusService = cardStatusService;
        this.cardRepository = cardRepository;
        this.cardNumGeneratorService = cardNumGeneratorService;
        this.transliterationService = transliterationService;
        this.cardValidityService = cardValidityService;
    }

    @Override
    @Transactional
    public Card createCard(long clientId, long accountId, long paymentSystemId) {
        Optional<Client> client = clientService.getClientById(clientId);
        Optional<Account> account = accountService.getAccountById(accountId);
        Optional<PaymentSystem> paymentSystem = paymentSystemService.getPaymentSystemById(paymentSystemId);
        Optional<CardStatus> cardStatus = cardStatusService.getCardStatusByCardStatusName("Card is not active");
        if (client.isPresent() && account.isPresent() && paymentSystem.isPresent()) {
            Card cardCreated = cardRepository.save(new Card(cardStatus.get(), paymentSystem.get(), account.get(),
                    client.get()));
            cardCreated.setCardNumber(cardNumGeneratorService.getCardNumber(cardCreated.getId(), paymentSystem.get()));
            cardCreated.setHolderName(transliterationService.getTransliterationName(client.get()));
            cardCreated.setExpirationDate(cardValidityService.getCardExpirationDate());
            return cardRepository.save(cardCreated);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Card> getAllCardsByClientId(long clientId) {
        Optional<Client> client = clientService.getClientById(clientId);
        return client.map(cardRepository::getAllByClient).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Card> getAllCardsByStatusId(long cardStatusId) {
        Optional<CardStatus> cardStatus = cardStatusService.getCardStatusById(cardStatusId);
        return cardStatus.map(cardRepository::getAllByCardStatus).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Card> getAllCardsByDateSentToProcessingCenter(Timestamp sentToProcessingCenter) {
        return cardRepository.getAllBySentToProcessingCenter(sentToProcessingCenter);
    }

    @Override
    @Transactional
    public void setDateSentToProcessingCenter(Timestamp sentToProcessingCenter, List<String> cardNumberList) {
        for (String cardNumber : cardNumberList) {
            Optional<Card> card = cardRepository.getByCardNumber(cardNumber);
            if (card.isPresent()) {
                card.get().setSentToProcessingCenter(sentToProcessingCenter);
                cardRepository.save(card.get());
            }
        }
    }

}
