package com.prosoft.salespoint.service;

import com.prosoft.salespoint.model.entity.TransactionType;

import java.util.Optional;

public interface TransactionTypeService {
    Optional<TransactionType> getTransactionType(String transactionTypeName);
}
