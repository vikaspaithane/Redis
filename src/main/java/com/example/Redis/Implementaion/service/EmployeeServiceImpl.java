package com.example.Redis.Implementaion.service;



import com.example.Redis.Implementaion.entity.Employee;
import com.example.Redis.Implementaion.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor // Constructor Injection automatic karel (Lombok)
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    @Transactional
    @CacheEvict(value = "employees", allEntries = true) // List cache clean kara
    public Employee saveEmployee(Employee employee) {
        log.info("MNC-LOG: Saving employee to database");
        return employeeRepository.save(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {
        log.info("MNC-LOG: Fetching all employees");
        return employeeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "employee", key = "#id", unless = "#result == null")
    public Employee getById(Long id) {
        log.info("MNC-LOG: Cache Miss! Fetching from DB for ID: {}", id);
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }

    @Override
    @Transactional
    @CachePut(value = "employee", key = "#id")
    @CacheEvict(value = "employees", allEntries = true)
    public Employee updateEmployee(Long id, Employee employeeDetails) {
        log.info("MNC-LOG: Updating employee and cache for ID: {}", id);

        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        existing.setName(employeeDetails.getName());
        existing.setSalary(employeeDetails.getSalary());
        existing.setGender(employeeDetails.getGender());

        return employeeRepository.save(existing);
    }

    @Override
    @Transactional
    @CacheEvict(value = "employee", key = "#id")
    public void deleteEmployee(Long id) {
        log.info("MNC-LOG: Deleting employee for ID: {}", id);
        employeeRepository.deleteById(id);
    }
}