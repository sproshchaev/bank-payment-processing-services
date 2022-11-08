package com.prosoft.issuingbank.repository;


import com.prosoft.issuingbank.model.entity.PaymentSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentSystemRepository extends JpaRepository<PaymentSystem, Long> {

}
