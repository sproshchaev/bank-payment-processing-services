package com.prosoft.processingcenter.repository;


import com.prosoft.processingcenter.model.entity.PaymentSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentSystemRepository extends JpaRepository<PaymentSystem, Long> {

}
