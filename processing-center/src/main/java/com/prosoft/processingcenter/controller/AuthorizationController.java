package com.prosoft.processingcenter.controller;

import com.prosoft.processingcenter.model.dto.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/authorization")
public class AuthorizationController {

    // private final static String AUTHORIZATION_URL =

    // внедряем сервис авторизации через конструктор
    public AuthorizationController() {
    }

    @GetMapping(value = "/tid/{tid}/date/{date}/card/{card}/expdate/{expdate}/sum/{sum}/curr/{curr}")
    public Payment paymentAuthorization(@PathVariable String date, @PathVariable String tid, @PathVariable String card,
                                        @PathVariable String expdate, @PathVariable String sum,
                                        @PathVariable String curr) {
        return new Payment(1, tid, date, card, expdate, sum, curr, "00", "123456");
    }  // todo id поправить - сейчас в константе

}
