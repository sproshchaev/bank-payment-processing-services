package com.prosoft.currencyconverter.service;

import com.prosoft.currencyconverter.model.dto.CurrencyExchangeRate;
import com.prosoft.currencyconverter.model.entity.ExchangeRate;
import com.prosoft.grpc.CurrencyConverterService;
import com.prosoft.grpc.CurrencyExchangeRateServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CurrencyExchangeRateServiceImpl extends CurrencyExchangeRateServiceGrpc.CurrencyExchangeRateServiceImplBase implements CurrencyExchangeRateService {
    private final String apiKey;
    private final RestTemplate restTemplate;
    private final ExchangeRateService exchangeRateService;
    private final CircuitBreakerFactory circuitBreakerFactory;

    @Autowired
    public CurrencyExchangeRateServiceImpl(@Value("${currency.rate.api-key}") String apiKey, RestTemplate restTemplate,
                                           ExchangeRateService exchangeRateService,
                                           CircuitBreakerFactory circuitBreakerFactory) {
        this.apiKey = apiKey;
        this.restTemplate = restTemplate;
        this.exchangeRateService = exchangeRateService;
        this.circuitBreakerFactory = circuitBreakerFactory;
    }

    public CurrencyExchangeRate getCurrencyExchangeRateExt(String currencyLetterCodeFrom, String currencyLetterCodeTo) {
        return circuitBreakerFactory.create("currencyExchangeRate").run(
                () -> mainRequest(currencyLetterCodeFrom, currencyLetterCodeTo),
                throwable -> fallBackRequest(currencyLetterCodeFrom, currencyLetterCodeTo));
    }

    private CurrencyExchangeRate mainRequest(String currencyLetterCodeFrom, String currencyLetterCodeTo) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", apiKey);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        Map responseMap = new HashMap<String, Object>();
        try {
            responseMap = restTemplate.exchange(
                    UriComponentsBuilder.fromHttpUrl("https://api.apilayer.com")
                            .path("/fixer/convert")
                            .queryParam("to", currencyLetterCodeTo)
                            .queryParam("from", currencyLetterCodeFrom)
                            .queryParam("amount", 1)
                            .build()
                            .toUri(),
                    HttpMethod.GET,
                    requestEntity,
                    Map.class).getBody();
            exchangeRateService.saveExchangeRate(((Map) responseMap.get("query")).get("from").toString(),
                    ((Map) responseMap.get("query")).get("to").toString(),
                    (double) responseMap.get("result"));
        } catch (HttpStatusCodeException ex) {
            ex.printStackTrace(); // todo вывод в лог информации об ошибках клиента и сервера (400 и 500 коды ответа)
        }
        return new CurrencyExchangeRate((String) responseMap.get("date"),
                ((Map) responseMap.get("query")).get("from").toString(),
                ((Map) responseMap.get("query")).get("to").toString(),
                (double) responseMap.get("result"));
    }

    private CurrencyExchangeRate fallBackRequest(String currencyLetterCodeFrom, String currencyLetterCodeTo) {
        Optional<ExchangeRate> currencyExchangeRateOptional = exchangeRateService
                .getCurrencyExchangeRateInt(currencyLetterCodeFrom, currencyLetterCodeTo);
        if (currencyExchangeRateOptional.isPresent()) {
            return new CurrencyExchangeRate(currencyExchangeRateOptional.get().getResponseDate().toString(),
                    currencyExchangeRateOptional.get().getCurrencyFrom().getCurrencyLetterCode(),
                    currencyExchangeRateOptional.get().getCurrencyTo().getCurrencyLetterCode(),
                    currencyExchangeRateOptional.get().getRate());
        } else {
            return new CurrencyExchangeRate("2022-11-15", currencyLetterCodeFrom, currencyLetterCodeTo, 1.0);
        }
    }

    @Override
    public void getCurrencyExchangeRate(CurrencyConverterService.RequestCurrencyExchangeRate request,
                                        StreamObserver<CurrencyConverterService.ResponseCurrencyExchangeRate> responseObserver) {
        CurrencyExchangeRate currencyExchangeRate = getCurrencyExchangeRateExt(request.getCurrencyLetterCodeFrom(),
                request.getCurrencyLetterCodeTo());
        CurrencyConverterService.ResponseCurrencyExchangeRate response = CurrencyConverterService.
                ResponseCurrencyExchangeRate
                .newBuilder()
                .setResponseDate(currencyExchangeRate.getResponseDate())
                .setCurrencyLetterCodeFrom(currencyExchangeRate.getCurrencyLetterCodeFrom())
                .setCurrencyLetterCodeTo(currencyExchangeRate.getCurrencyLetterCodeTo())
                .setRate(currencyExchangeRate.getRate())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
