package com.prosoft.processingcenter.repository;


import com.prosoft.processingcenter.model.entity.ResponseCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseCodeRepository extends JpaRepository<ResponseCode, Long> {

}
