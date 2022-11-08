package com.prosoft.issuingbank.repository;


import com.prosoft.issuingbank.model.entity.CardStatus;
import com.prosoft.issuingbank.model.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardStatusRepository extends JpaRepository<CardStatus, Long> {

    Optional<CardStatus> findByCardStatusName(String cardStatusName);

}
