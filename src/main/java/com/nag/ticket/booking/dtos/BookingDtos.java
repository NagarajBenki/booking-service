package com.nag.ticket.booking.dtos;

import com.nag.ticket.booking.enums.BookingStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
public class BookingDtos {

    private UUID bookingId;

    @NotNull(message = "User Id Mandotory")
    @Positive(message = "Please provide a valid user id")
    private String userId;

    @NotNull(message = "Movie Id Mandotory")
    @Positive(message = "Please provide a valid movie id")
    private Integer movieId;

    @NotNull(message = "You need to select antleast one seat")
    private List<String> seatsSelected;

    @NotNull(message = "Select the show date")
    private LocalDate showDate;

    @NotNull(message = "Select the show time")
    private LocalTime showTime;

    @Positive(message = "Booking amount positive")
    private Double bookingAmount;

    private BookingStatus bookingStatus;

}
