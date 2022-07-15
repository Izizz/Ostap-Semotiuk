package com.epam.repairstuidiospring.controller;

import com.epam.repairstuidiospring.controller.exception.ServiceException;
import com.epam.repairstuidiospring.controller.exception.UserException;
import com.epam.repairstuidiospring.service.model.Error;
import com.epam.repairstuidiospring.service.model.enums.ErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ErrorHandlingController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<Error> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("handleMethodArgumentNotValidException: exception {}", ex.getMessage(), ex);
        return ex.getBindingResult().getAllErrors().stream()
                .map(err -> new Error(err.getDefaultMessage(), ErrorType.VALIDATION_ERROR_TYPE, LocalDateTime.now()))
                .collect(Collectors.toList());
    }


//    @ExceptionHandler(NullPointerException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public String handleNullPointerException(NullPointerException ex) {
//        log.error("handleNullPointerException: exception {}", ex.getMessage(), ex);
//        return ex.getMessage();
//    }

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handleUserException(UserException ex, HandlerMethod hm) {
        log.error("handleUserException: exception: {}, method: {}",
                ex.getMessage(), hm.getMethod(), ex);
        return new Error(ex.getMessage(), ex.getErrorType(), LocalDateTime.now());
    }
}