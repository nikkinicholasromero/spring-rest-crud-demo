package com.demo.controller;

import com.demo.dto.ErrorResponse;
import com.demo.exception.EmployeeNotFound;
import jakarta.validation.ConstraintViolationException;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@ResponseBody
@ControllerAdvice
public class ExceptionHandlerControllerAdvice {
    @ExceptionHandler(EmployeeNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ProblemDetail handleException(EmployeeNotFound e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail handleException(ConstraintViolationException e) {
        List<ErrorResponse> errors = e.getConstraintViolations()
                .stream()
                .map(constraintViolation -> new ErrorResponse(
                        ((PathImpl) constraintViolation.getPropertyPath()).getLeafNode().getName(),
                        constraintViolation.getMessage()))
                .collect(Collectors.toList());

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        problemDetail.setProperty("errors", errors);
        return problemDetail;
    }
}
