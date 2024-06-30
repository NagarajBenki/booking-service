package com.nag.ticket.booking.service;

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

    @Override
    @Transactional
    public ResponseDto createBooking(BookingDtos bookingDtos) {
        log.info("Enter into create new booking service impl with the request data " + bookingDtos);
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
        log.info("new booking created in booking service impl " + entity);
        return ResponseDto.builder().bookingDtos(BookingDtos.builder()
                .bookingAmount(entity.getBookingAmount())
                .userId(entity.getUserId())
                .movieId(entity.getMovieId())
                .showTime(entity.getShowTime())
                .bookingStatus(entity.getBookingStatus())
                .seatsSelected(entity.getSeatsSelected())
                .showDate(entity.getShowDate())
                .build()).build();
    }
}
