package com.prosoft.processingcenter.repository;


import com.prosoft.processingcenter.model.entity.Account;
import com.prosoft.processingcenter.model.entity.Card;
import com.prosoft.processingcenter.model.entity.Transaction;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @EntityGraph(attributePaths = {"account", "transactionType"})
    List<Transaction> getTransactionByAccount(Account account);

    @EntityGraph(attributePaths = {"account", "transactionType"})
    List<Transaction> getAllBySentToIssuingBank(Timestamp sentToIssuingBank);

    @EntityGraph(attributePaths = {"account", "transactionType"})
    List<Transaction> getAllByReceivedFromIssuingBankAndSentToIssuingBank(Timestamp receivedFromIssuingBank,
                                                                          Timestamp sentToIssuingBank);
    @EntityGraph(attributePaths = {"account", "transactionType"})
    List<Transaction> getAllByCard(Card card);

}
