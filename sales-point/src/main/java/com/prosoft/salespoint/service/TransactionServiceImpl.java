package com.prosoft.salespoint.service;

import com.prosoft.salespoint.model.entity.*;
import com.prosoft.salespoint.model.vo.PaymentValueObject;
import com.prosoft.salespoint.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionTypeService transactionTypeService;
    private final TerminalService terminalService;
    private final ResponseCodeService responseCodeService;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  TransactionTypeService transactionTypeService, TerminalService terminalService, ResponseCodeService responseCodeService) {
        this.transactionRepository = transactionRepository;
        this.transactionTypeService = transactionTypeService;
        this.terminalService = terminalService;
        this.responseCodeService = responseCodeService;
    }

    @Override
    @Transactional
    public Transaction createPaymentTransaction(PaymentValueObject paymentValueObject) {
        Optional<TransactionType> transactionTypeOptional = transactionTypeService
                .getTransactionType("Payment");
        Optional<Terminal> terminalOptional = terminalService.getByTerminalId(paymentValueObject.getTerminalId());
        Optional<ResponseCode> responseCodeOptional = responseCodeService
                .getResponseCodeByErrorCode(paymentValueObject.getErrorCode());
        if (transactionTypeOptional.isPresent() && terminalOptional.isPresent() && responseCodeOptional.isPresent()) {
            return transactionRepository.save(new Transaction(
                    stringToDate(paymentValueObject.getTransactionDate()),
                    Double.parseDouble(paymentValueObject.getSum()),
                    transactionTypeOptional.get(),
                    new Card(paymentValueObject.getCardNumber()),
                    terminalOptional.get(),
                    responseCodeOptional.get(),
                    paymentValueObject.getAuthorizationCode()));
        } else {
            return null;
        }
    }

    private Date stringToDate(String date) {
        try {
            return new Date(new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
