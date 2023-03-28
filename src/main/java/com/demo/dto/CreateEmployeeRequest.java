package com.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public record CreateEmployeeRequest(
        String firstName,
        String lastName,
        long numberOfDependents,
        BigDecimal height,
        BigDecimal weight,
        LocalDate hiredDate,
        LocalTime startTime,
        boolean isRegular
) {
}
