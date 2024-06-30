package com.nag.ticket.booking.service;

import com.nag.ticket.booking.brokers.PaymentServiceBroker;
import com.nag.ticket.booking.dtos.BookingDtos;
import com.nag.ticket.booking.dtos.ResponseDto;
import com.nag.ticket.booking.entities.BookingEntity;
import com.nag.ticket.booking.enums.BookingStatus;
import com.nag.ticket.booking.repositories.BookingRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookingServiceImpl implements  BookingService {

    @Autowired
    private BookingRepository bookingRepository;


    @Autowired
    private PaymentServiceBroker paymentService;

    @Override
    @Transactional
    public ResponseDto createBooking(BookingDtos bookingDtos) {
        log.info("Enter into create new booking service impl with the request data {}",bookingDtos.toString());
//        BookingEntity entity = new BookingEntity();
//        entity.setMovieId(bookingDtos.getMovieId());
//        entity.setBookingAmount(bookingDtos.getBookingAmount());
//        entity.setShowDate(bookingDtos.getShowDate());
//        entity.setShowTime(bookingDtos.getShowTime());
//        entity.setBookingStatus(BookingStatus.PENDING);
//        entity.setSeatsSelected(bookingDtos.getSeatsSelected());
//        bookingRepository.save(entity);

        //With builder class
        BookingEntity entity = BookingEntity.builder()
                .bookingAmount(bookingDtos.getBookingAmount())
                .userId(bookingDtos.getUserId())
                .movieId(bookingDtos.getMovieId())
                .showTime(bookingDtos.getShowTime())
                .bookingStatus(BookingStatus.PENDING)
                .seatsSelected(bookingDtos.getSeatsSelected())
                .showDate(bookingDtos.getShowDate())
                .build();
        bookingRepository.save(entity);
        bookingDtos.setBookingId(entity.getBookingId());
        log.info("new booking created in booking service impl {}",entity.toString());
        log.info("calling payment service for booking id {} and payment amount {}",bookingDtos.getBookingId(), bookingDtos.getBookingAmount());
        BookingDtos bookingDtos1 = paymentService.makePayment(bookingDtos);
        entity.setBookingStatus(bookingDtos1.getBookingStatus());
        log.info("payment service completed and got response {}", bookingDtos1.toString());
        return ResponseDto.builder().bookingDtos(BookingDtos.builder()
                        .userId(entity.getUserId())
                        .bookingId(entity.getBookingId())
                        .bookingAmount(entity.getBookingAmount())
                        .userId(entity.getUserId())
                        .movieId(entity.getMovieId())
                        .showTime(entity.getShowTime())
                        .bookingStatus(bookingDtos1.getBookingStatus())
                        .seatsSelected(entity.getSeatsSelected())
                        .showDate(entity.getShowDate())
                        .build()).build();
    }
}
