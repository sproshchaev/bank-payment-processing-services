package com.prosoft.processingcenter.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentSystem extends JpaRepository<PaymentSystem, Long> {

}
