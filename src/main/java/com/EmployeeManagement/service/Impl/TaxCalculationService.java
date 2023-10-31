package com.EmployeeManagement.service.Impl;

import com.EmployeeManagement.entity.Employee;
import com.EmployeeManagement.entity.TaxInfo;
import com.EmployeeManagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaxCalculationService  {
    @Autowired
    private EmployeeRepository employeeRepository;


    public List<TaxInfo> calculateTaxDeductions() {
        List<Employee> employees = employeeRepository.findAll();
        List<TaxInfo> taxInfos = new ArrayList<>();

        for (Employee employee : employees) {
            double yearlySalary = calculateYearlySalary(employee);
            double taxAmount = calculateTaxAmount(yearlySalary);
            double cessAmount = calculateCessAmount(yearlySalary);

            TaxInfo taxInfo = new TaxInfo();
            taxInfo.setEmployeeCode(employee.getEmployeeId());
            taxInfo.setFirstName(employee.getFirstName());
            taxInfo.setLastName(employee.getLastName());
            taxInfo.setYearlySalary(yearlySalary);
            taxInfo.setTaxAmount(taxAmount);
            taxInfo.setCessAmount(cessAmount);

            taxInfos.add(taxInfo);
        }

        return taxInfos;
    }

    private double calculateYearlySalary(Employee employee) {
        // Calculate yearly salary based on DOJ and salary
        LocalDate today = LocalDate.now();
        int currentYear = today.getYear();
        int dojYear = employee.getDoj().getYear();
        int monthsWorked = 12 - employee.getDoj().getMonthValue() + 1;

        // Exclude the months before the employee's joining date in the current year
        if (currentYear == dojYear) {
            monthsWorked = 12 - employee.getDoj().getMonthValue() + 1;
        }

        double monthlySalary = employee.getSalary();
        double yearlySalary = monthlySalary * monthsWorked;

        return yearlySalary;
    }

    private double calculateTaxAmount(double yearlySalary) {
        double taxAmount = 0.0;

        if (yearlySalary <= 250000) {
            taxAmount = 0;
        } else if (yearlySalary <= 500000) {
            taxAmount = (yearlySalary - 250000) * 0.05;
        } else if (yearlySalary <= 1000000) {
            taxAmount = 250000 * 0.05 + (yearlySalary - 500000) * 0.10;
        } else {
            taxAmount = 250000 * 0.05 + 500000 * 0.10 + (yearlySalary - 1000000) * 0.20;
        }

        return taxAmount;
    }

    private double calculateCessAmount(double yearlySalary) {
        // Implement 2% cess calculation logic for the amount exceeding 2,500,000
        double cessAmount = 0.0;
        double threshold = 2500000;

        if (yearlySalary > threshold) {
            cessAmount = (yearlySalary - threshold) * 0.02;
        }

        return cessAmount;
    }

}
