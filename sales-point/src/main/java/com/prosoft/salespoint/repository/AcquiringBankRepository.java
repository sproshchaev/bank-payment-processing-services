package com.prosoft.salespoint.repository;


import com.prosoft.salespoint.model.entity.AcquiringBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcquiringBankRepository extends JpaRepository<AcquiringBank, Long> {

}
