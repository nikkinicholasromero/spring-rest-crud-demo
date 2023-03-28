package com.demo.dto;

import java.math.BigDecimal;

public record EmployeeDto(
        String id,
        String firstName,
        String lastName,
        long numberOfDependents,
        BigDecimal height,
        BigDecimal weight) {
}
