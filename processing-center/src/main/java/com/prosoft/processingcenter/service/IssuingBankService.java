package com.prosoft.processingcenter.service;

import com.prosoft.processingcenter.model.entity.IssuingBank;

import java.util.Optional;

public interface IssuingBankService {

    Optional<IssuingBank> getIssuingBankById(long issuingBankId);

}
