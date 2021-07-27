package com.example.accounting.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RankMapper implements RowMapper<Rank> {
    @Override
    public Rank mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Rank(
                rs.getInt("rank_id"),
                rs.getString("rank_name"),
                rs.getBigDecimal("rank_salary")
        );
    }
}
