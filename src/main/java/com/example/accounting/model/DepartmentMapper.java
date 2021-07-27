package com.example.accounting.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentMapper implements RowMapper<DepartmentJdbc> {
    @Override
    public DepartmentJdbc mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new DepartmentJdbc(
                rs.getInt("dep_id"),
                rs.getString("dep_name"),
                rs.getString("dep_phone"),
                rs.getString("dep_email"),
                rs.getInt("dep_boss_id")
        );
    }
}
