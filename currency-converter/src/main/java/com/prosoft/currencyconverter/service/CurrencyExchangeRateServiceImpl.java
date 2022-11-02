package com.prosoft.currencyconverter.service;

import com.prosoft.currencyconverter.model.dto.CurrencyExchangeRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
public class CurrencyExchangeRateServiceImpl implements CurrencyExchangeRateService {
    private final String apiKey;
    private final RestTemplate restTemplate;

    @Autowired
    public CurrencyExchangeRateServiceImpl(@Value("${currency.rate.api-key}") String apiKey, RestTemplate restTemplate) {
        this.apiKey = apiKey;
        this.restTemplate = restTemplate;
    }

    public CurrencyExchangeRate getCurrencyExchangeRate(String currencyLetterCodeFrom, String currencyLetterCodeTo) {
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
                            .queryParam("amount", 1) // todo передавать необходимую сумму и получать значение?
                            .build()
                            .toUri(),
                    HttpMethod.GET,
                    requestEntity,
                    Map.class).getBody();
        } catch (HttpStatusCodeException ex) {
            ex.printStackTrace();
        }
        return new CurrencyExchangeRate((String) responseMap.get("date"),
                ((Map) responseMap.get("query")).get("from").toString(),
                ((Map) responseMap.get("query")).get("to").toString(),
                (double) responseMap.get("result"));
    }
}
