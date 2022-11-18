package com.prosoft.salespoint.service;

import com.prosoft.salespoint.model.entity.ResponseCode;
import com.prosoft.salespoint.repository.ResponseCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ResponseCodeServiceImpl implements ResponseCodeService {
    private final ResponseCodeRepository responseCodeRepository;

    @Autowired
    public ResponseCodeServiceImpl(ResponseCodeRepository responseCodeRepository) {
        this.responseCodeRepository = responseCodeRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ResponseCode> getResponseCodeByErrorCode(String errorCode) {
        return responseCodeRepository.getResponseCodeByErrorCode(errorCode);
    }
}
