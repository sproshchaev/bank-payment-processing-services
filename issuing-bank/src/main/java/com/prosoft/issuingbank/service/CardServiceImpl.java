package com.prosoft.issuingbank.service;

import com.prosoft.issuingbank.model.dto.CardDto;
import com.prosoft.issuingbank.model.entity.*;
import com.prosoft.issuingbank.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private final BankSettingService bankSettingService;

    @Autowired
    public CardServiceImpl(ClientService clientService, AccountService accountService,
                           PaymentSystemService paymentSystemService, CardStatusService cardStatusService,
                           CardRepository cardRepository, CardNumGeneratorService cardNumGeneratorService,
                           TransliterationService transliterationService, CardValidityService cardValidityService, BankSettingService bankSettingService) {
        this.clientService = clientService;
        this.accountService = accountService;
        this.paymentSystemService = paymentSystemService;
        this.cardStatusService = cardStatusService;
        this.cardRepository = cardRepository;
        this.cardNumGeneratorService = cardNumGeneratorService;
        this.transliterationService = transliterationService;
        this.cardValidityService = cardValidityService;
        this.bankSettingService = bankSettingService;
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
            cardCreated.setCardNumber(cardNumGeneratorService.getCardNumber(cardCreated.getId(), paymentSystem.get(),
                    bankSettingService.getBySetting("bin").get().getCurrentValue()));
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
        return client.map(cardRepository::getAllByClient).get();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Card> getAllCardsByStatusId(long cardStatusId) {
        Optional<CardStatus> cardStatus = cardStatusService.getCardStatusById(cardStatusId);
        return cardStatus.map(cardRepository::getAllByCardStatus).get();
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

    @Override
    @Transactional
    public void getCardFromProcessingCenter(CardDto[] cardDtoArray) {
        System.out.println("\nMessage read from newCardToIssuingBank: \n" + Arrays.stream(cardDtoArray)
                .map(c -> c.toString()).collect(Collectors.joining(", \n")));
        Timestamp receivedFromProcessingCenter = new Timestamp(System.currentTimeMillis());
        List<CardDto> cardDtoList = Arrays.stream(cardDtoArray).collect(Collectors.toList());
        cardDtoList.forEach(c -> {
            Optional<CardStatus> cardStatusOptional = cardStatusService.getCardStatusByCardStatusName(c.getCardStatusName());
            Optional<Card> cardOptional = cardRepository.getByCardNumber(c.getCardNumber());
            if (cardOptional.isPresent() && cardStatusOptional.isPresent()) {
                cardOptional.get().setCardStatus(cardStatusOptional.get());
                cardOptional.get().setReceivedFromProcessingCenter(receivedFromProcessingCenter);
                cardRepository.save(cardOptional.get());
            } else {
                // todo gen. exception
                System.out.println("Reject: Ошибка в параметрах карты " + c.getCardNumber() + ": "
                        + "  - card=" + cardOptional.isPresent() + "\n"
                        + "  - cardStatus=" + cardStatusOptional.isPresent() + "\n");
            }
        });
    }


}
