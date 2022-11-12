package com.prosoft.processingcenter.repository;


import com.prosoft.processingcenter.model.entity.Card;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    @EntityGraph(attributePaths = {"account"})
    Optional<Card> getCardByCardNumber(String cardNumber);

}
