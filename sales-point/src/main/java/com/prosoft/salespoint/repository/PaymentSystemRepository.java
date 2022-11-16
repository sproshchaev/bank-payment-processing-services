package com.prosoft.salespoint.repository;


import com.prosoft.salespoint.model.entity.PaymentSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentSystemRepository extends JpaRepository<PaymentSystem, Long> {

}
