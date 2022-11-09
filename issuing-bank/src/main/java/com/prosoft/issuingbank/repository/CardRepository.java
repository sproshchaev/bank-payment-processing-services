package com.prosoft.issuingbank.repository;


import com.prosoft.issuingbank.model.entity.Card;
import com.prosoft.issuingbank.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> getAllByClient(Client client);
}
