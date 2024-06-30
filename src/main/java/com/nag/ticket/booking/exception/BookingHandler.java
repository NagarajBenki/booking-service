package com.nag.ticket.booking.exception;

import com.nag.ticket.booking.dtos.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class BookingHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpEntity<ResponseDto> methodArgumentNotValidException(MethodArgumentNotValidException exception){
        log.info("Entered into booking handler class "+exception.getMessage());
       /* List<ObjectError> errors =  exception.getBindingResult().getAllErrors();
        List<String> errorMessages = new ArrayList<>();
        for(ObjectError error : errors){
            errorMessages.add(error.getDefaultMessage());
        }
        ResponseDto responseDto = ResponseDto.builder().
                                    errorMessages(errorMessages).build();*/

//        Using streams
        ResponseDto responseDto = ResponseDto.builder()
                        .errorDescription(HttpStatus.BAD_REQUEST.getReasonPhrase())
                        .errorMessages(exception.getBindingResult().getAllErrors()
//                        .stream().map(objectError->objectError.getDefaultMessage())
                        .stream().map(ObjectError::getDefaultMessage)
                        .collect(Collectors.toList())).build();
        return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.BAD_REQUEST);
    }
}
