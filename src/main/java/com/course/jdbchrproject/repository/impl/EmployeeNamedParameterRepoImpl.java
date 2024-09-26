package com.course.jdbchrproject.repository.impl;

import com.course.jdbchrproject.entity.Employee;
import com.course.jdbchrproject.mapper.EmployeeMapper;
import com.course.jdbchrproject.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
@Primary
public class EmployeeNamedParameterRepoImpl implements EmployeeRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public int count() {
        String sql = "select count(*) from employee";
        return namedParameterJdbcTemplate.queryForObject(sql, new HashMap<>(), Integer.class);
    }

    @Override
    public Employee findById(long id) {
        return namedParameterJdbcTemplate.
                queryForObject("select id,name,salary from employee where id =:id",
                        new MapSqlParameterSource("id", id), new EmployeeMapper());
    }

    @Override
    public List<Employee> findAll() {
        return namedParameterJdbcTemplate.query("select id,name,salary from employee", new EmployeeMapper());
    }

    @Override
    public int save(Employee employee) {
        return namedParameterJdbcTemplate.update("insert into employee (id,name,salary) values(:id,:name,:salary)",
                new BeanPropertySqlParameterSource(employee));
    }

    @Override
    public int update(Employee employee) {
        return namedParameterJdbcTemplate.update("update employee set name = :name, salary = :salary",
                new BeanPropertySqlParameterSource(employee));
    }

    @Override
    public int deleteById(long id) {
        return namedParameterJdbcTemplate.update("delete from employee where id = :id",
                new MapSqlParameterSource("id", id)
        );
    }

    @Override
    public List<Employee> findByNameAndSalary(String name, double salary) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("name", name);
        mapSqlParameterSource.addValue("salary", salary);
        return namedParameterJdbcTemplate.
                query("select id,name,salary from employee where name = :name and salary =:salary",
                        mapSqlParameterSource, new EmployeeMapper());
    }

    @Override
    public List<Employee> findByJobTitle(String jobTitle) {
        return namedParameterJdbcTemplate.query("select id,name,salary from employee where job_title =:jobTitle",
                new MapSqlParameterSource("jobTitle", jobTitle), new EmployeeMapper());
    }

    @Override
    public boolean existById(long id) {
        boolean b = namedParameterJdbcTemplate.queryForObject(
                "select count(*) from employee where id =:id",
                new MapSqlParameterSource("id", id),
                Integer.class) > 0;
        return b;
    }
}




