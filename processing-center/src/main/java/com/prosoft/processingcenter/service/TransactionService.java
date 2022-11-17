package com.prosoft.processingcenter.service;

import com.prosoft.processingcenter.model.dto.PaymentDto;
import com.prosoft.processingcenter.model.dto.TransactionDto;
import com.prosoft.processingcenter.model.entity.Account;
import com.prosoft.processingcenter.model.entity.Card;
import com.prosoft.processingcenter.model.entity.Transaction;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface TransactionService {

    void getTransactionFromIssuingBank(TransactionDto[] transactionDtoArray);

    List<Transaction> getAllTransactionsByAccountId(Account account);

    double getBalanceFromTransactions(Account account);

    Optional<Transaction> createTransaction(Card card, PaymentDto paymentDto, String authorizationCode);

    List<Transaction> getAllTransactionsByDateSentToIssuingBank(Timestamp sentToIssuingBank);

    void setDateSentToIssuingBank(Timestamp sentToProcessingCenter, List<Long> transactionIdList);

}
