package com.prosoft.currencyconverter.service;

import com.prosoft.currencyconverter.model.dto.CurrencyExchangeRate;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.prosoft.grpc.CurrencyExchangeRateServiceGrpc;
import com.prosoft.grpc.CurrencyConverterService;


import java.util.HashMap;
import java.util.Map;

@Service
public class CurrencyExchangeRateServiceImpl extends CurrencyExchangeRateServiceGrpc.CurrencyExchangeRateServiceImplBase
        implements CurrencyExchangeRateService {
    private final String apiKey;
    private final RestTemplate restTemplate;

    public CurrencyExchangeRateServiceImpl(@Value("${currency.rate.api-key}")String apiKey, RestTemplate restTemplate) {
        this.apiKey = apiKey;
        this.restTemplate = restTemplate;
    }

   @Override
   public CurrencyExchangeRate getCurrencyExchangeRateExt(String currencyLetterCodeFrom, String currencyLetterCodeTo) {
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
                            .queryParam("amount", 1) // todo можно передавать необходимую сумму и получать значение (т.е. расчет суммы списания на стороне currency-converter)
                            .build()
                            .toUri(),
                    HttpMethod.GET,
                    requestEntity,
                    Map.class).getBody();
        } catch (HttpStatusCodeException ex) {
            ex.printStackTrace(); // todo вывод в лог информации об ошибках клиента и сервера (400 и 500 коды ответа)
        }
        return new CurrencyExchangeRate((String) responseMap.get("date"),
                ((Map) responseMap.get("query")).get("from").toString(),
                ((Map) responseMap.get("query")).get("to").toString(),
                (double) responseMap.get("result"));
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
