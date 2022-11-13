package com.prosoft.processingcenter.service;

import com.prosoft.processingcenter.model.dto.Payment;
import com.prosoft.processingcenter.model.dto.TransactionDto;
import com.prosoft.processingcenter.model.entity.Account;
import com.prosoft.processingcenter.model.entity.Card;
import com.prosoft.processingcenter.model.entity.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {

    void getTransactionFromIssuingBank(TransactionDto[] transactionDtoArray);

    List<Transaction> getAllTransactionsByAccountId(Account account);

    double getBalanceFromTransactions(Account account);

    Optional<Transaction> createTransaction(Card card, Payment payment, String authorizationCode);

}
