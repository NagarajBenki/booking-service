package com.nag.ticket.booking.brokers;

import com.nag.ticket.booking.dtos.BookingDtos;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service",url = "http://localhost:9093/payments/get")
public interface PaymentServiceBroker {

    @PostMapping
    public BookingDtos makePayment(@RequestBody BookingDtos bookingDtos);

}
