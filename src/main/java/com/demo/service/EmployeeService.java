package com.demo.service;

import com.demo.database.entity.Employee;
import com.demo.database.repository.EmployeeRepository;
import com.demo.dto.EmployeeDto;
import com.demo.exception.EmployeeNotFound;
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

    public EmployeeDto findById(String id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFound("Employee with id " + id + " not found. "));

        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName());
    }
}
