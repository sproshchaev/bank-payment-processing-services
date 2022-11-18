package com.prosoft.salespoint.service;

import com.prosoft.salespoint.model.entity.ResponseCode;

import java.util.Optional;

public interface ResponseCodeService {

   Optional<ResponseCode> getResponseCodeByErrorCode(String errorCode);

}
