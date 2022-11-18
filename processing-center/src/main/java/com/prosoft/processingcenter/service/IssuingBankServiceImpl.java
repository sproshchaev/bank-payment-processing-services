package com.prosoft.processingcenter.service;

import com.prosoft.processingcenter.model.entity.IssuingBank;
import com.prosoft.processingcenter.repository.IssuingBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IssuingBankServiceImpl implements IssuingBankService {
    private final IssuingBankRepository issuingBankRepository;

    @Autowired
    public IssuingBankServiceImpl(IssuingBankRepository issuingBankRepository) {
        this.issuingBankRepository = issuingBankRepository;
    }

    @Override
    public Optional<IssuingBank> getIssuingBankById(long issuingBankId) {
        return issuingBankRepository.findById(issuingBankId);
    }
}
