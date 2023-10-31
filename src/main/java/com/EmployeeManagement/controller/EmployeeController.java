package com.EmployeeManagement.controller;
import com.EmployeeManagement.entity.Employee;
import com.EmployeeManagement.entity.TaxInfo;
import com.EmployeeManagement.service.EmployeeService;
import com.EmployeeManagement.service.Impl.TaxCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private TaxCalculationService taxCalculationService;

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee employee) {
        Employee savedEmployee = employeeService.saveEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/tax-deductions")
    public ResponseEntity<List<TaxInfo>> calculateTaxDeductions() {
        List<TaxInfo> taxDeductions = taxCalculationService.calculateTaxDeductions();
        return ResponseEntity.ok(taxDeductions);
    }



}
