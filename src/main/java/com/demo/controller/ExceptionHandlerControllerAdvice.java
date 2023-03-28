package com.demo.controller;

import com.demo.exception.EmployeeNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody
@ControllerAdvice
public class ExceptionHandlerControllerAdvice {
    @ExceptionHandler(EmployeeNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ProblemDetail handleException(EmployeeNotFound e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    }
}
