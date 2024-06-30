package com.nag.ticket.booking.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nag.ticket.booking.brokers.PaymentServiceBroker;
import com.nag.ticket.booking.dtos.BookingDtos;
import com.nag.ticket.booking.dtos.ResponseDto;
import com.nag.ticket.booking.service.BookingService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PaymentServiceBroker paymentService;

    @PostMapping("/create_booking")
    public ResponseEntity<ResponseDto> createBooking(@Valid @RequestBody  BookingDtos booking){
        log.info("Entered into booking controller to create a new booking "+booking);
        System.out.println(paymentService.createPayment());
        return new ResponseEntity<>(bookingService.createBooking(booking), HttpStatus.CREATED);
    }

    @PostMapping("/create_booking1")
    public ResponseEntity<ResponseDto> createBooking1(@RequestBody  String booking) throws JsonProcessingException {
        log.info("Entered into booking controller to create a new booking "+booking);
        // to convert string json to object
        BookingDtos dto = objectMapper.readValue(booking,BookingDtos.class);
        String data = objectMapper.writeValueAsString(dto);
        return new ResponseEntity<>(bookingService.createBooking(dto), HttpStatus.CREATED);
    }

}
