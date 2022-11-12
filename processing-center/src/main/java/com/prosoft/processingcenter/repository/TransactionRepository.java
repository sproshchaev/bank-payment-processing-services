package com.prosoft.processingcenter.repository;


import com.prosoft.processingcenter.model.entity.Account;
import com.prosoft.processingcenter.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> getTransactionByAccount(Account account);
}
