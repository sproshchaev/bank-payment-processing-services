package com.prosoft.salespoint.service;

import com.prosoft.salespoint.model.entity.Transaction;
import com.prosoft.salespoint.model.vo.PaymentValueObject;

public interface TransactionService {

    Transaction createPaymentTransaction(PaymentValueObject paymentValueObject);

}
