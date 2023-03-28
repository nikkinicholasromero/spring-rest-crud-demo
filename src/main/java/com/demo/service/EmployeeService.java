package com.demo.service;

import com.demo.database.entity.Employee;
import com.demo.database.repository.EmployeeRepository;
import com.demo.dto.CreateEmployeeRequest;
import com.demo.dto.EmployeeDto;
import com.demo.dto.UpdateEmployeeRequest;
import com.demo.exception.EmployeeNotFound;
import com.demo.transformer.EmployeeTransformer;
import jakarta.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Service
@Validated
public class EmployeeService {
    private static final String EMPLOYEE_NOT_FOUND = "Employee with id %s not found. ";

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
        Employee employee = new Employee(
                UUID.randomUUID().toString(),
                StringUtils.trimToEmpty(request.firstName()),
                StringUtils.trimToEmpty(request.lastName()),
                request.numberOfDependents(),
                request.height(),
                request.weight(),
                request.hiredDate(),
                request.startTime(),
                request.isRegular(),
                "ADMIN");

        employeeRepository.save(employee);
    }

    @Transactional
    public void updateEmployee(String id, @Valid UpdateEmployeeRequest request) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFound(EMPLOYEE_NOT_FOUND.formatted(id)));

        employee.updateName(
                StringUtils.trimToEmpty(request.firstName()),
                StringUtils.trimToEmpty(request.lastName()));
        employee.updateNumberOfDependents(request.numberOfDependents());
        employee.updateBodyInformation(request.height(), request.weight());
        employee.updateEmploymentInformation(request.hiredDate(), request.startTime(), request.isRegular());

        employeeRepository.save(employee);
    }

    @Transactional
    public void deleteEmployee(String id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFound(EMPLOYEE_NOT_FOUND.formatted(id)));

        employee.delete("ADMIN");

        employeeRepository.save(employee);
    }
}
