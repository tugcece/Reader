package com.patika.readerorderservice.exception;

import com.patika.readerorderservice.dto.response.ExceptionResponse;
import com.patika.readerorderservice.dto.response.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ReaderyException.class)
    public GenericResponse<ExceptionResponse> handleException(ReaderyException exception) {
        log.error(exception.getLocalizedMessage());
        return GenericResponse.failed(exception.getMessage());
    }

}
