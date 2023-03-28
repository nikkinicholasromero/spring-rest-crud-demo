package com.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public record EmployeeDto(
        String id,
        String firstName,
        String lastName,
        long numberOfDependents,
        BigDecimal height,
        BigDecimal weight,
        LocalDate hiredDate,
        LocalTime startTime) {
}
