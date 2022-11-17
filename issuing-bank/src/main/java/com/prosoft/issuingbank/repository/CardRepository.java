package com.prosoft.issuingbank.repository;


import com.prosoft.issuingbank.model.entity.Card;
import com.prosoft.issuingbank.model.entity.CardStatus;
import com.prosoft.issuingbank.model.entity.Client;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    @EntityGraph(attributePaths = {"account", "cardStatus"})
    List<Card> getAllByClient(Client client);

    @EntityGraph(attributePaths = {"account", "cardStatus"})
    List<Card> getAllByCardStatus(CardStatus cardStatus);

    @EntityGraph(attributePaths = {"account", "cardStatus"})
    List<Card> getAllBySentToProcessingCenter(Timestamp sentToProcessingCenter);

    @EntityGraph(attributePaths = {"account", "cardStatus"})
    Optional<Card> getByCardNumber(String cardNumber);

}
