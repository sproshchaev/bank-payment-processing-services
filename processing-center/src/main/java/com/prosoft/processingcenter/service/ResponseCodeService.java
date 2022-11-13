package com.prosoft.processingcenter.service;

import com.prosoft.processingcenter.model.entity.ResponseCode;

import java.util.Optional;

public interface ResponseCodeService {

    Optional<ResponseCode> getByErrorCode(String errorCode);

}
