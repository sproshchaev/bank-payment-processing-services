package com.prosoft.salespoint.repository;


import com.prosoft.salespoint.model.entity.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionTypeRepository extends JpaRepository<TransactionType, Long> {

    Optional<TransactionType> getTransactionTypeByTransactionTypeName(String transactionTypeName);

}
