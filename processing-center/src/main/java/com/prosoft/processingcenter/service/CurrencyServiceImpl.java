package com.prosoft.processingcenter.service;

import com.prosoft.grpc.CurrencyConverterService;
import com.prosoft.grpc.CurrencyExchangeRateServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Override
    public String getCourse(String from, String to) {

        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:9090") // todo вынести в настройки
                .usePlaintext().build();

        CurrencyExchangeRateServiceGrpc.CurrencyExchangeRateServiceBlockingStub stub =
                CurrencyExchangeRateServiceGrpc.newBlockingStub(channel);

        CurrencyConverterService.RequestCurrencyExchangeRate request = CurrencyConverterService.RequestCurrencyExchangeRate
                .newBuilder()
                .setCurrencyLetterCodeFrom(from)
                .setCurrencyLetterCodeTo(to)
                .build();

        CurrencyConverterService.ResponseCurrencyExchangeRate response =
                stub.getCurrencyExchangeRate(request);

        //System.out.println("Получено: " + response);

        channel.shutdownNow();

        return response.toString();
    }

}
