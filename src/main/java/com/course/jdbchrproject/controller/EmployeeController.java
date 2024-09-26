package com.course.jdbchrproject.controller;

import com.course.jdbchrproject.entity.Employee;
import com.course.jdbchrproject.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/count")
    public int countEmployees(){
        return employeeRepository.count();
    }
    @GetMapping("/{id}")
    public Employee findById(@PathVariable long id){
        return employeeRepository.findById(id);
    }
    @GetMapping("")
    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }
    @GetMapping("/totalSalary")
    public double calculateTotalSalary(){
        return employeeRepository.calculateTotalSalary();
    }
    @GetMapping("/jobTitle/{jobTitle}")
    public List<Employee> findByJobTitle(@PathVariable String jobTitle){
        return employeeRepository.findByJobTitle(jobTitle);
    }
    @GetMapping("/exist/{id}")
    public boolean existById(@PathVariable long id){
        return employeeRepository.existById(id);
    }
}
