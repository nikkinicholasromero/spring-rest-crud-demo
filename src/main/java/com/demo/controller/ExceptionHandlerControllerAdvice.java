package com.demo.controller;

import com.demo.dto.ErrorResponse;
import com.demo.exception.EmployeeNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Locale;

@ResponseBody
@ControllerAdvice
public class ExceptionHandlerControllerAdvice {
    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(EmployeeNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ProblemDetail handleException(EmployeeNotFound e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail handleException(MethodArgumentNotValidException e) {
        List<ErrorResponse> errors = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new ErrorResponse(fieldError.getField(), getLocalizedMessage(fieldError.getDefaultMessage())))
                .toList();

        ProblemDetail problemDetail = e.getBody();
        problemDetail.setProperty("errors", errors);
        return problemDetail;
    }

    private String getLocalizedMessage(String code) {
        return messageSource.getMessage(code, new Object[]{}, code, Locale.getDefault());
    }
}
