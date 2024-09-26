package com.course.jdbchrproject;

import com.course.jdbchrproject.entity.Employee;
import com.course.jdbchrproject.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@SpringBootApplication
public class JdbcHrProjectApplication implements CommandLineRunner {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(JdbcHrProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (employeeRepository.count() == 0) {
			employeeRepository.save(new Employee(1L, "Alex", 4000,"Accountant"));
			employeeRepository.save(new Employee(2L, "Ragheb", 5000,"Engineer"));
			employeeRepository.save(new Employee(3L, "Ahmed", 6000,"Doctor"));
			employeeRepository.save(new Employee(4L, "Donia", 7000,"Teacher"));
		}
		System.out.println("Data inserted successfully");
		List<Employee> employees = employeeRepository.findAll();
		System.out.println("Employees:");
		for (Employee employee : employees) {
			System.out.println("ID: " + employee.getId() + ", Name: " + employee.getName() + "," +
					" Salary: " + employee.getSalary() + ", Job Title: " + employee.getJob_title());
		}
	}
}
