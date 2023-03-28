package com.demo.database.repository;

import com.demo.database.entity.Employee;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends Repository<Employee, String> {
    List<Employee> findAll();

    Optional<Employee> findById(String id);
}
