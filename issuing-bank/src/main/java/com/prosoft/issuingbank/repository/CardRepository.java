package com.prosoft.issuingbank.repository;


import com.prosoft.issuingbank.model.entity.Card;
import com.prosoft.issuingbank.model.entity.CardStatus;
import com.prosoft.issuingbank.model.entity.Client;
import com.prosoft.issuingbank.model.entity.Transaction;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> getAllByClient(Client client);

    List<Card> getAllByCardStatus(CardStatus cardStatus);

    List<Card> getAllBySentToProcessingCenter(Timestamp sentToProcessingCenter);

    Optional<Card> getByCardNumber(String cardNumber);

}
