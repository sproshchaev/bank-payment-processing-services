package com.prosoft.issuingbank.service;

import com.prosoft.issuingbank.model.entity.BankSetting;
import com.prosoft.issuingbank.repository.BankSettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankSettingServiceImpl implements BankSettingService {

    private final  BankSettingRepository bankSettingRepository;

    @Autowired
    public BankSettingServiceImpl(BankSettingRepository bankSettingRepository) {
        this.bankSettingRepository = bankSettingRepository;
    }

    @Override
    public Optional<BankSetting> getBySetting(String setting) {
        return bankSettingRepository.getBySetting(setting);
    }
}
