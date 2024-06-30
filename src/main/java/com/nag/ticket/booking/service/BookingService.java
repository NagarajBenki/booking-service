package com.nag.ticket.booking.service;

import com.nag.ticket.booking.dtos.BookingDtos;
import com.nag.ticket.booking.dtos.ResponseDto;

public interface BookingService {
    public ResponseDto createBooking(BookingDtos bookingDtos);
}
