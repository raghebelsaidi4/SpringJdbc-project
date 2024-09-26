package com.course.jdbchrproject.repository.impl;

import com.course.jdbchrproject.entity.Employee;
import com.course.jdbchrproject.mapper.EmployeeMapper;
import com.course.jdbchrproject.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Repository
@Qualifier("EmployeeRepoImpl")
public class EmployeeRepoImpl implements EmployeeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from employee", Integer.class);
    }

    @Override
    public Employee findById(long id) {
        return jdbcTemplate.queryForObject("select id,name,salary from employee where id =?", new Object[]{id}, new EmployeeMapper());
    }

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query("select id,name,salary from employee", new EmployeeMapper());
    }

    @Override
    public int save(Employee employee) {
        return jdbcTemplate.update("insert into employee (id,name,salary) values(?,?,?)",
                new Object[]{employee.getId(), employee.getName(), employee.getSalary()});
    }

    @Override
    public int update(Employee employee) {
        return jdbcTemplate.update("update employee set name = ?, salary = ?",
                new Object[]{employee.getName(), employee.getSalary()});
    }

    @Override
    public int deleteById(long id) {
        return jdbcTemplate.update("delete from employee where id = ?",
                new Object[]{id}
        );
    }

    @Override
    public List<Employee> findByNameAndSalary(String name, double salary) {
        return (List<Employee>) Collections.singletonList(jdbcTemplate.query(
                "select id, name, salary from employee where name = ? and salary = ?",
                new Object[]{name, salary},
                (rs) -> {
                    Employee employee = new Employee();
                    employee.setId(rs.getLong("id"));
                    employee.setName(rs.getString("name"));
                    employee.setSalary(rs.getDouble("salary"));
                    return employee;
                }));
    }

    @Override
    public List<Employee> findByJobTitle(String jobTitle) {

        return jdbcTemplate.query("select id,name,salary from employee where job_title =?",
                new Object[]{jobTitle}, new EmployeeMapper());
    }

    @Override
    public boolean existById(long id) {
        boolean b = jdbcTemplate.queryForObject("select count(*) from employee where id =?"
                , new Object[]{id}, Integer.class) > 0;
        return b;
    }
}



