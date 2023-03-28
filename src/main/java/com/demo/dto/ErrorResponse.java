package com.demo.dto;

public record ErrorResponse(
        String code,
        String message) {
}
