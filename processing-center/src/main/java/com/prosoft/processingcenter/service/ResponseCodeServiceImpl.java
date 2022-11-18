package com.prosoft.processingcenter.service;

import com.prosoft.processingcenter.model.entity.ResponseCode;
import com.prosoft.processingcenter.repository.ResponseCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResponseCodeServiceImpl implements ResponseCodeService {

    private final ResponseCodeRepository responseCodeRepository;

    @Autowired
    public ResponseCodeServiceImpl(ResponseCodeRepository responseCodeRepository) {
        this.responseCodeRepository = responseCodeRepository;
    }

    @Override
    public Optional<ResponseCode> getByErrorCode(String errorCode) {
        return responseCodeRepository.getByErrorCode(errorCode);
    }
}
