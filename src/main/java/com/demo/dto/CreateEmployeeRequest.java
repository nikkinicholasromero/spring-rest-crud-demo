package com.demo.dto;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public record CreateEmployeeRequest(
        @NotBlank(message = "errors.first.name.required.error") String firstName,
        String lastName,
        long numberOfDependents,
        BigDecimal height,
        BigDecimal weight,
        LocalDate hiredDate,
        LocalTime startTime,
        boolean isRegular) {
}
