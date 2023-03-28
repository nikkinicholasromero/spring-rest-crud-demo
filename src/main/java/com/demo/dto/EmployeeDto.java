package com.demo.dto;

public record EmployeeDto(
        String id,
        String firstName,
        String lastName,
        long numberOfDependents) {
}
