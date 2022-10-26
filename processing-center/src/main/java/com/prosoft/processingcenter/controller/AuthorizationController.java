package com.prosoft.processingcenter.controller;

import com.prosoft.processingcenter.model.dto.Payment;
import com.prosoft.processingcenter.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/authorization")
public class AuthorizationController {

    private final AuthorizationService authorizationService;

    @Autowired
    public AuthorizationController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @GetMapping(value = "/tid/{tid}/date/{date}/card/{card}/expdate/{expdate}/sum/{sum}/curr/{curr}")
    public Payment paymentAuthorization(@PathVariable String date, @PathVariable String tid, @PathVariable String card,
                                        @PathVariable String expdate, @PathVariable String sum,
                                        @PathVariable String curr) {
        return authorizationService.paymentAuthorization(new Payment(tid, date, card, expdate, sum, curr));
    }

}
