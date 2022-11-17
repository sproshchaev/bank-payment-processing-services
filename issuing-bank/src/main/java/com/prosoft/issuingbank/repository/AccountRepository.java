package com.prosoft.issuingbank.repository;


import com.prosoft.issuingbank.model.entity.Account;
import com.prosoft.issuingbank.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> getAllByClient(Client client);
    Optional<Account> getAccountByAccountNumber(String accountNumber);

}
