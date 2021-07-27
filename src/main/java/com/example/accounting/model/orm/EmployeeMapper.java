package com.example.accounting.model.orm;

import com.example.accounting.model.RankMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Employee(
                new com.example.accounting.model.EmployeeMapper()
                        .mapRow(rs, rowNum),
                new DepartmentMapper().mapRow(rs, rowNum),
                new RankMapper().mapRow(rs, rowNum)
        );
    }
}
