package com.demo.service;

import com.demo.dto.EmployeeDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    public List<EmployeeDto> findAll() {
        return List.of();
    }
}
