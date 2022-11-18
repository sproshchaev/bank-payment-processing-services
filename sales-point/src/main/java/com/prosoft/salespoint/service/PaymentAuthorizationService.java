package com.prosoft.salespoint.service;

import com.prosoft.salespoint.model.vo.PaymentValueObject;

public interface PaymentAuthorizationService {
    PaymentValueObject makeAuthorization(PaymentValueObject requestPaymentValueObject);

}
