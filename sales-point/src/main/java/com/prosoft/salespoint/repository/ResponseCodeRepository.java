package com.prosoft.salespoint.repository;


import com.prosoft.salespoint.model.entity.ResponseCode;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResponseCodeRepository extends JpaRepository<ResponseCode, Long> {

    Optional<ResponseCode> getResponseCodeByErrorCode(String errorCode);

}
