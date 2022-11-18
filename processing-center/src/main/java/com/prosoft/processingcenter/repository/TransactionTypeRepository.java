package com.prosoft.processingcenter.repository;


import com.prosoft.processingcenter.model.entity.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionTypeRepository extends JpaRepository<TransactionType, Long> {

    Optional<TransactionType> findByTransactionTypeName(String transactionTypeName);

}
