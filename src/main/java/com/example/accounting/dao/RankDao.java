package com.example.accounting.dao;

import com.example.accounting.model.Rank;
import com.example.accounting.model.RankMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Component("rankDao")
public class RankDao implements CrudDao<Integer, Rank> {
    private static final String GET_BY_ID_SQL = "select * from rank where rank_id = ?;";

    private static final String DELETE_BY_ID_SQL = "delete from rank where rank_id = ?;";

    private static final String INSERT_SQL = "insert into rank (rank_name, rank_salary)" +
            " values(?, ?);";

    private static final String UPDATE_SQL = "update rank set rank_name=?, rank_salary=?\n" +
            "where rank_id=?;";
    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public RankDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Rank get(Integer id) {
        return jdbcTemplate.queryForObject(GET_BY_ID_SQL, new RankMapper(), id);
    }

    @Override
    public List<Rank> getAll() {
        return jdbcTemplate.query("select * from rank;", new RankMapper());
    }

    @Override
    public Optional<Rank> findByName(String name) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("select * from rank where rank_name=?;", new RankMapper(), name));
    }

    @Override
    public void create(Rank obj) {
        jdbcTemplate.update(
                INSERT_SQL,
                obj.getName(), obj.getSalary()
        );
    }

    @Override
    public boolean delete(Integer id) {
        return jdbcTemplate.update(DELETE_BY_ID_SQL, id) == 1;
    }

    @Override
    public void update(Rank obj) {
        jdbcTemplate.update(UPDATE_SQL, obj.getName(), obj.getSalary(), obj.getId());
    }
}
