package com.nag.ticket.booking.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL )
public class ResponseDto {
    private String errorDescription;
    private List<String> errorMessages;
    private BookingDtos bookingDtos;
    private String statusCodeDescription;
}
