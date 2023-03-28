package com.demo.controller;

import com.demo.dto.CreateEmployeeRequest;
import com.demo.dto.EmployeeDto;
import com.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<EmployeeDto> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public EmployeeDto findById(@PathVariable String id) {
        return employeeService.findById(id);
    }

    @PostMapping
    public void createEmployee(@RequestBody CreateEmployeeRequest request) {

    }
}
