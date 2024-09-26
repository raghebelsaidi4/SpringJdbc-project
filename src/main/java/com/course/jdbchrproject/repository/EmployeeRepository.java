package com.course.jdbchrproject.repository;

import com.course.jdbchrproject.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository {

    int count();

    Employee findById(long id);

    List<Employee> findAll();

    int save(Employee employee);

    int update(Employee employee);

    int deleteById(long id);

    List<Employee> findByNameAndSalary(String name, double salary);

    default int calculateTotalSalary(){
        return findAll().stream().
                mapToInt(employee -> (int) employee.getSalary()).sum();
    }
    List<Employee> findByJobTitle(String jobTitle);

    //check if employee exist
    boolean existById(long id);



}
