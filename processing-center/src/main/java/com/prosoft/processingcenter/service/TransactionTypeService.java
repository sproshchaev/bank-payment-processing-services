package com.prosoft.processingcenter.service;

import com.prosoft.processingcenter.model.entity.TransactionType;

import java.util.Optional;

public interface TransactionTypeService {

    Optional<TransactionType> getByTransactionTypeName(String transactionTypeName);

}
