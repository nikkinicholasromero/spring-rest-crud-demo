package com.demo.service;

import com.demo.database.entity.EmployeeRepository;
import com.demo.dto.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeDto> findAll() {
        return employeeRepository.findAll().stream()
                .map(employee -> new EmployeeDto(
                        employee.getId(),
                        employee.getFirstName(),
                        employee.getLastName()))
                .toList();
    }
}
