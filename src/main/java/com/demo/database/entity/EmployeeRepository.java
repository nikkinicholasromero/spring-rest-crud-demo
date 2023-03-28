package com.demo.database.entity;

import org.springframework.data.repository.Repository;

import java.util.List;

public interface EmployeeRepository extends Repository<Employee, String> {
    List<Employee> findAll();

    Employee findById(String id);
}
