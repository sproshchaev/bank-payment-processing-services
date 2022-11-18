package com.prosoft.processingcenter.repository;


import com.prosoft.processingcenter.model.entity.AcquiringBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcquiringBankRepository extends JpaRepository<AcquiringBank, Long> {

}
