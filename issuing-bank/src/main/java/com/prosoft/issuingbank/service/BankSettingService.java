package com.prosoft.issuingbank.service;

import com.prosoft.issuingbank.model.entity.BankSetting;

import java.util.Optional;

public interface BankSettingService {

    Optional<BankSetting> getBySetting(String setting);

}
