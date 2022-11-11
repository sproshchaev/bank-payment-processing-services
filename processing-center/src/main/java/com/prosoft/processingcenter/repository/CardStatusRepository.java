package com.prosoft.processingcenter.repository;


import com.prosoft.processingcenter.model.entity.CardStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardStatusRepository extends JpaRepository<CardStatus, Long> {

    Optional<CardStatus> getCardStatusByCardStatusName(String cardStatusName);

}
