package com.prosoft.issuingbank.service;

import com.prosoft.issuingbank.model.entity.TransactionType;
import com.prosoft.issuingbank.repository.TransactionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionTypeServiceImpl implements TransactionTypeService {
    private final TransactionTypeRepository transactionTypeRepository;

    @Autowired
    public TransactionTypeServiceImpl(TransactionTypeRepository transactionTypeRepository) {
        this.transactionTypeRepository = transactionTypeRepository;
    }

    @Override
    public Optional<TransactionType> getTransactionTypeById(long transactionTypeId) {
        return transactionTypeRepository.findById(transactionTypeId);
    }
}
