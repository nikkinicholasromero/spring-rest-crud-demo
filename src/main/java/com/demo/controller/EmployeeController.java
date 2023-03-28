package com.demo.controller;

import com.demo.dto.EmployeeDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @GetMapping
    public List<EmployeeDto> findAll() {
        return List.of();
    }
}
