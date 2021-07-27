package com.example.accounting.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeMapper implements RowMapper<EmployeeJdbc> {
    @Override
    public EmployeeJdbc mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new EmployeeJdbc(
                rs.getInt("emp_id"),
                rs.getString("emp_lastname"),
                rs.getString("emp_firstname"),
                rs.getString("emp_patronymic"),
                rs.getInt("emp_dep_id"),
                rs.getInt("emp_rank_id")
        );
    }
}
