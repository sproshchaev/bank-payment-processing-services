package com.prosoft.processingcenter.repository;


import com.prosoft.processingcenter.model.entity.ResponseCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResponseCodeRepository extends JpaRepository<ResponseCode, Long> {

    Optional<ResponseCode> getByErrorCode(String errorCode);

}
