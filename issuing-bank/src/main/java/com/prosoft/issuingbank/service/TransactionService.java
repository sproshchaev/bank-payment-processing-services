package com.prosoft.issuingbank.service;

import com.prosoft.issuingbank.model.entity.Transaction;

import java.util.List;

public interface TransactionService {

   List<Transaction> getAllTransactionsByAccountId(long accountId);

   Transaction createTransactionByAccountId(long accountId, long transactionTypeId, double sum, String transactionName);

}
