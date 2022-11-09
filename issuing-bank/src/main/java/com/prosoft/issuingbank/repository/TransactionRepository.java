package com.prosoft.issuingbank.repository;


import com.prosoft.issuingbank.model.entity.Account;
import com.prosoft.issuingbank.model.entity.Transaction;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @EntityGraph(attributePaths = {"account", "transactionType"})
    List<Transaction> getAllByAccount(Account account);
}
