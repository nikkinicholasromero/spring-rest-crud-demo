package com.demo.controller;

import com.demo.dto.EmployeeDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    @GetMapping
    public List<EmployeeDto> getAllEmployees() {
        return List.of();
    }
}
