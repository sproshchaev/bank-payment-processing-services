package com.prosoft.currencyconverter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/currency")
public class CurrencyController {

/*
    @GetMapping(value = "/tid/{tid}/date/{date}/card/{card}/expdate/{expdate}/sum/{sum}/curr/{curr}")
    public Payment paymentAuthorization(@PathVariable String tid, @PathVariable String date, @PathVariable String card,
                                        @PathVariable String expdate, @PathVariable String sum,
                                        @PathVariable String curr) {
        return authorizationService.paymentAuthorization(new Payment(tid, date, card, expdate, sum, curr));
    }
*/

}
