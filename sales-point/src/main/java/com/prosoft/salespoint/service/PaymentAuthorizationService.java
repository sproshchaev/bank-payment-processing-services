package com.prosoft.salespoint.service;

import com.prosoft.salespoint.model.dto.PaymentValueObject;

public interface PaymentAuthorizationService {
    PaymentValueObject makeAuthorization(PaymentValueObject requestPaymentValueObject);

}
