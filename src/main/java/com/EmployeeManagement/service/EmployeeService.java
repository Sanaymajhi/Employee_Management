package com.EmployeeManagement.service;

import com.EmployeeManagement.entity.Employee;

import java.util.List;

public interface EmployeeService  {
    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
}
