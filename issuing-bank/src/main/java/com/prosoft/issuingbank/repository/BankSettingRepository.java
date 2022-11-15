package com.prosoft.issuingbank.repository;

import com.prosoft.issuingbank.model.entity.BankSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankSettingRepository extends JpaRepository<BankSetting, Long> {

    Optional<BankSetting> getBySetting(String setting);

}
