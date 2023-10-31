package com.EmployeeManagement.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;
@Data@AllArgsConstructor@NoArgsConstructor
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    private String email;

    @ElementCollection
    private List<@Pattern(regexp = "^[0-9]*$") String> phoneNumbers;

    @NotNull
    private LocalDate doj;

    @DecimalMin("0.0")
    private double salary;
}