package com.prosoft.issuingbank.service;

import com.prosoft.issuingbank.model.dto.TransactionDto;
import com.prosoft.issuingbank.model.entity.Account;
import com.prosoft.issuingbank.model.entity.Transaction;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface TransactionService {

   List<Transaction> getAllTransactionsByAccountId(long accountId);

   Transaction createTransactionByAccountId(long accountId, long transactionTypeId, double sum, String transactionName);

   List<Transaction> getAllTransactionsByDateSentToProcessingCenter(Timestamp sentToProcessingCenter);

   void setDateSentToProcessingCenter(Timestamp sentToProcessingCenter, List<Long> transactionIdList);

   void getTransactionFromProcessingCenter(TransactionDto[] transactionDtoArray);

   double getBalanceFromTransactions(Account account);

}
