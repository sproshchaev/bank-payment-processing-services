package com.prosoft.salespoint.feign;

import com.prosoft.salespoint.model.vo.PaymentValueObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "processing-center", path = "/authorization")
public interface AuthorizationServiceProxy {

    @GetMapping(value = "/tid/{tid}/date/{date}/card/{card}/expdate/{expdate}/sum/{sum}/curr/{curr}")
    PaymentValueObject paymentAuthorization(@PathVariable String tid, @PathVariable String date,
                                            @PathVariable String card, @PathVariable String expdate,
                                            @PathVariable String sum, @PathVariable String curr);

}
