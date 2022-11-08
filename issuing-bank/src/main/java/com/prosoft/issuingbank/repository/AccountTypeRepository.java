package com.prosoft.issuingbank.repository;


import com.prosoft.issuingbank.model.entity.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {
    Optional<AccountType> findByAccountTypeName(String accountTypeName);
}
