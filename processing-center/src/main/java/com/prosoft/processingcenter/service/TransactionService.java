package com.prosoft.processingcenter.service;

import com.prosoft.processingcenter.model.dto.TransactionDto;
import com.prosoft.processingcenter.model.entity.Account;
import com.prosoft.processingcenter.model.entity.Transaction;

import java.util.List;

public interface TransactionService {

    void getTransactionFromIssuingBank(TransactionDto[] transactionDtoArray);

    List<Transaction> getAllTransactionsByAccountId(Account account);

    double getBalanceFromTransactions(Account account);

}
