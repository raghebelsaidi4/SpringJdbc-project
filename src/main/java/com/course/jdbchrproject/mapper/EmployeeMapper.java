package com.course.jdbchrproject.mapper;

import com.course.jdbchrproject.entity.Employee;
import org.springframework.jdbc.core.RowMapper;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Employee(rs.getLong("id"), rs.getString("name"),
                rs.getDouble("salary"),  rs.getString("job_title"));
    }
}
