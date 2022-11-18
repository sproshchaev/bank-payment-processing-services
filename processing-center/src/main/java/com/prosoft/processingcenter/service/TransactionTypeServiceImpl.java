package com.prosoft.processingcenter.service;

import com.prosoft.processingcenter.model.entity.TransactionType;
import com.prosoft.processingcenter.repository.TransactionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TransactionTypeServiceImpl implements TransactionTypeService {

    private final TransactionTypeRepository transactionTypeRepository;

    @Autowired
    public TransactionTypeServiceImpl(TransactionTypeRepository transactionTypeRepository) {
        this.transactionTypeRepository = transactionTypeRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TransactionType> getByTransactionTypeName(String transactionTypeName) {
        return transactionTypeRepository.findByTransactionTypeName(transactionTypeName);
    }
}
