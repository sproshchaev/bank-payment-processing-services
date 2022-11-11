package com.prosoft.processingcenter.repository;


import com.prosoft.processingcenter.model.entity.IssuingBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssuingBankRepository extends JpaRepository<IssuingBank, Long> {

}
