package com.prosoft.issuingbank.service;

import com.prosoft.issuingbank.model.entity.TransactionType;

import java.util.Optional;

public interface TransactionTypeService {

    Optional<TransactionType> getTransactionTypeById(long transactionTypeId);
    Optional<TransactionType> getByTransactionTypeName(String transactionTypeName);

}
