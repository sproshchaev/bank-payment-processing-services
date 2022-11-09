package com.prosoft.issuingbank.repository;


import com.prosoft.issuingbank.model.entity.Transaction;
import com.prosoft.issuingbank.model.entity.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionTypeRepository extends JpaRepository<TransactionType, Long> {

}
