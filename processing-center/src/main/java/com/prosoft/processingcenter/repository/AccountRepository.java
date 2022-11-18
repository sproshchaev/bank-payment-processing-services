package com.prosoft.processingcenter.repository;


import com.prosoft.processingcenter.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> getAccountByAccountNumber(String accountNumber);

}
