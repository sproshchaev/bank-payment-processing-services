package com.prosoft.processingcenter.service;

import com.prosoft.grpc.CurrencyConverterService;
import com.prosoft.grpc.CurrencyExchangeRateServiceGrpc;
import com.prosoft.processingcenter.model.entity.Currency;
import com.prosoft.processingcenter.repository.CurrencyRepository;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    private final CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public Optional<Double> getCourse(String from, String to) {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:9090") // todo вынести в настройки
                .usePlaintext().build();

        CurrencyExchangeRateServiceGrpc.CurrencyExchangeRateServiceBlockingStub stub =
                CurrencyExchangeRateServiceGrpc.newBlockingStub(channel);

        CurrencyConverterService.RequestCurrencyExchangeRate request = CurrencyConverterService
                .RequestCurrencyExchangeRate
                .newBuilder()
                .setCurrencyLetterCodeFrom(from)
                .setCurrencyLetterCodeTo(to)
                .build();

        CurrencyConverterService.ResponseCurrencyExchangeRate response = stub.getCurrencyExchangeRate(request);

        channel.shutdownNow();

        return Optional.of(response.getRate());
    }

    @Override
    public Optional<Currency> getCurrencyByCurrencyLetterCode(String currencyLetterCode) {
        return currencyRepository.getCurrencyByCurrencyLetterCode(currencyLetterCode);
    }

    @Override
    public Optional<Double> convertSum(double sum, String fromCurrencyLetterCode, String toCurrencyLetterCode) {
        return Optional.ofNullable(getCourse(fromCurrencyLetterCode, toCurrencyLetterCode)
                .map(c -> c * sum).get());
    }

}
