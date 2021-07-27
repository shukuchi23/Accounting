package com.example.accounting.model.orm;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentMapper implements RowMapper<Department> {

    @Override
    public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Department(
                new com.example.accounting.model.DepartmentMapper()
                        .mapRow(rs, rowNum)
        );
    }
}
