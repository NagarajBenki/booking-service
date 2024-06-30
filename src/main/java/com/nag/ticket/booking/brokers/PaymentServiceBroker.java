package com.nag.ticket.booking.brokers;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "payment-service",url = "http://localhost:9093/payments/get")
public interface PaymentServiceBroker {

    @GetMapping
    public String createPayment();

}
