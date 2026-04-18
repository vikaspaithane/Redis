package com.example.Redis.Implementaion.controller;

import com.example.Redis.Implementaion.entity.Employee;
import com.example.Redis.Implementaion.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees") // Standard: Base path with versioning
@RequiredArgsConstructor             // Standard: Constructor Injection (Lombok)
@Slf4j                               // Standard: Logging
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        log.info("MNC-LOG: Request received to save employee: {}", employee.getName());
        Employee saved = employeeService.saveEmployee(employee);

        // Standard: Post request sathi CREATED (201) status dene changle asate
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployee() {
        log.info("MNC-LOG: Request received to fetch all employees");
        List<Employee> allEmployees = employeeService.getAllEmployees();
        return ResponseEntity.ok(allEmployees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        log.info("MNC-LOG: Request received to fetch employee with ID: {}", id);
        Employee employee = employeeService.getById(id);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        log.info("MNC-LOG: Request received to update employee ID: {}", id);
        Employee updated = employeeService.updateEmployee(id, employee);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        log.info("MNC-LOG: Request received to delete employee ID: {}", id);
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted successfully!");
    }
}