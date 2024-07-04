package com.patika.readerinvoiceservice.exception;

import com.patika.readerinvoiceservice.dto.response.ExceptionResponse;
import com.patika.readerinvoiceservice.dto.response.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ReaderException.class)
    public GenericResponse<ExceptionResponse> handleException(ReaderException exception) {
        log.error(exception.getLocalizedMessage());
        return GenericResponse.failed(exception.getMessage());
    }

}
