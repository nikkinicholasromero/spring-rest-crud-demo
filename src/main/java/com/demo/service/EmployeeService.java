package com.demo.service;

import com.demo.database.entity.Employee;
import com.demo.database.repository.EmployeeRepository;
import com.demo.dto.CreateEmployeeRequest;
import com.demo.dto.EmployeeDto;
import com.demo.dto.UpdateEmployeeRequest;
import com.demo.exception.EmployeeNotFound;
import com.demo.transformer.EmployeeTransformer;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Service
@Validated
public class EmployeeService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    private static final String EMPLOYEE_NOT_FOUND = "Employee with id %s not found. ";
    private static final String ADMIN = "ADMIN";

    private final EmployeeRepository employeeRepository;
    private final EmployeeTransformer employeeTransformer;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeTransformer employeeTransformer) {
        this.employeeRepository = employeeRepository;
        this.employeeTransformer = employeeTransformer;
    }

    public List<EmployeeDto> findAll() {
        return employeeRepository.findAll().stream()
                .map(employeeTransformer::transform)
                .toList();
    }

    public EmployeeDto findById(String id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFound(EMPLOYEE_NOT_FOUND.formatted(id)));

        return employeeTransformer.transform(employee);
    }

    @Transactional
    public void createEmployee(@Valid CreateEmployeeRequest request) {
        logger.info("Creating employee {}", request);

        Employee employee = new Employee(
                UUID.randomUUID().toString(),
                request.firstName(),
                request.lastName(),
                request.numberOfDependents(),
                request.height(),
                request.weight(),
                request.hiredDate(),
                request.startTime(),
                request.isRegular(),
                ADMIN);

        employeeRepository.save(employee);

        logger.info("Employee {} created", employee);
    }

    @Transactional
    public void updateEmployee(String id, @Valid UpdateEmployeeRequest request) {
        logger.info("Updating employee with id {} using values {}", id, request);

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFound(EMPLOYEE_NOT_FOUND.formatted(id)));

        employee.updateName(request.firstName(), request.lastName(), ADMIN);
        employee.updateNumberOfDependents(request.numberOfDependents(), ADMIN);
        employee.updateBodyInformation(request.height(), request.weight(), ADMIN);
        employee.updateEmploymentInformation(request.hiredDate(), request.startTime(), request.isRegular(), ADMIN);

        employeeRepository.save(employee);

        logger.info("Employee with id {} updated using values {}", id, request);
    }

    @Transactional
    public void deleteEmployee(String id) {
        logger.info("Deleting employee with id {}", id);

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFound(EMPLOYEE_NOT_FOUND.formatted(id)));

        employee.delete(ADMIN);

        employeeRepository.save(employee);

        logger.info("Employee with id {} deleted", id);
    }
}
