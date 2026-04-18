package com.example.Redis.Implementaion.service;



import com.example.Redis.Implementaion.entity.Employee;
import java.util.List;

/**
 * Service interface for Employee management.
 * Follows the standard CRUD contract.
 */
public interface EmployeeService {

    // Employee save karne kiwa create karne
    Employee saveEmployee(Employee employee);

    // Sarv employees chi list milvane
    List<Employee> getAllEmployees();

    // Specific employee ID nusar shodhane
    Employee getById(Long id);

    // Employee update karne
    Employee updateEmployee(Long id, Employee employee);

    // Employee delete karne
    void deleteEmployee(Long id);
}